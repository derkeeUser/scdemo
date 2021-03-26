package com.springcloud.mq.scrabbitmq.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.sccommon.exception.BusinessException;
import com.springcloud.mq.scrabbitmq.entity.Message;
import com.springcloud.mq.scrabbitmq.entity.Queue;
import com.springcloud.mq.scrabbitmq.mapper.MessageMapper;
import com.springcloud.mq.scrabbitmq.mapper.QueueMapper;
import com.springcloud.mq.scrabbitmq.service.MessageService;
import com.springcloud.mq.scrabbitmq.util.SnowFlake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author duzhou
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final QueueMapper queueMapper;
    private final SnowFlake snowFlake;
    private final RabbitTemplate rabbitTemplate;

    /**
     * 添加预发送消息
     * @param consumerQueue 消费队列
     * @param messageBody   消息内容
     * @return
     */
    @Override
    public Long addWaitSendMessage(String consumerQueue, String messageBody) {
        // 判断消费队列是否为空
        if (!StringUtils.isNotBlank(consumerQueue)) {
            throw new BusinessException("消费队列不能为空");
        }

        // 判断消息内容是否为空
        if (!StringUtils.isNotBlank(messageBody)) {
            throw new BusinessException("消息内容不能为空");
        }

        // 保存消息内容到数据库
        Message message = new Message();
        message.setId(snowFlake.nextId());
        message.setConsumerQueue(consumerQueue);
        message.setMessageBody(messageBody);
        message.setResendTimes(0);  // 重发次数
        message.setAlreadyDead(0);  // 是否已死（0:否、1:是）
        message.setStatus(0);       // 消息状态（0:未发送 1:已发送）
        message.setConfirmTime(null);
        Date now = new Date();
        message.setCreateTime(now);
        message.setUpdateTime(now);
        messageMapper.insert(message);

        // 返回消息ID
        return message.getId();
    }

    /**
     * 确认并发送消息
     * @param messageId 消息id
     */
    @Override
    public void confirmAndSendMessage(Long messageId) {
        // 判断消息id是否为空
        if (null == messageId) {
            throw new BusinessException("消息id不能为空");
        }

        // 获取消息
        Message message = messageMapper.selectById(messageId);
        if (null == message) {
            throw new BusinessException("未找到消息记录，请检查参数是否正确");
        }

        // 更新消息状态为发送中
        message.setStatus(1);
        // 更新发送时间
        message.setConfirmTime(new Date());
        messageMapper.updateById(message);

        // 向MQ发送消息
        rabbitTemplate.convertAndSend("shop-account-topic-exchange", message.getConsumerQueue(), message);
    }

    /**
     * 处理所有长时间未确认的消息，和上游业务系统确认是否发送该消息
     */
    @Override
    public void checkWaitingMessage() {
        log.info("处理所有长时间未确认的消息开始 >>>>>>>>>>>>");

        log.info("1. 查找所有配置的队列");
        // 1. 查找所有配置的队列
        List<Queue> queues = queueMapper.selectList(new Queue());

        log.info("2. 根据队列查找响应的消息，对未确认的消息进行重发");
        // 2. 根据队列查找响应的消息
        for (Queue queue : queues) {
            // 2.1 根据队列查找消息状态为 0:未确认 状态，并且超过指定时间的消息
            // 计算创建时间止（当前时间 - 我们设定的超时毫秒），如果消息的创建时间小于“创建时间止”，那么就说明超时了
            Date createEndTime = DateUtil.offset(new Date(), DateField.MILLISECOND, 18000);
            List<Message> messages = messageMapper.selectTooLongUnConfirmMessageList(queue.getConsumerQueue(), createEndTime);

            // 3. 遍历消息，对未确认的消息进行重发
            for (Message message : messages) {
                // 3.1 调用业务方http接口确认消息是否需要发送
                /**
                 * 未确认的消息，有3种可能
                 *   1. 消息持久化之后，返回应答失败的
                 *   2. 返回应答后，业务系统执行业务失败的
                 *   3. 业务系统的业务执行完了，返回确认消息失败的
                 * 所以，业务方需要提供一个接口对上面3个情况进行判断，然后返回结果给我们确定是继续发送还是删除
                 */
                String checkResult = HttpUtil.post(queue.getCheckUrl(), message.getMessageBody());
                Integer result = Integer.parseInt(checkResult);

                // 3.2 需要发送，进行发送
                if (1 == result) {
                    confirmAndSendMessage(message.getId());
                }

                // 3.3 不需要发送，直接删除
                if (0 == result) {
                    delete(message.getId());
                }
            }
        }

        log.info("处理所有长时间未确认的消息结束 >>>>>>>>>>>>");
    }

    /**
     * 消息恢复功能，对于长时间未消费的消息，重新发送消息给下层业务系统
     */
    @Override
    public void checkUnConsumeMessage() {
        log.info("处理所有长时间未消费的消息开始 >>>>>>>>>>>>");

        /**
         * timeInterval 重发时间间隔
         * 举例： [4, 10, 30, 60, 120, 360]
         * 消息确认会下下游业务发送首次消息，4分钟内，下游业务没有确认消费该消息，则消息恢复子系统会重发该消息。
         * 再过10分钟（也就是消息确认后14分钟内），下游业务没有确认消费该消息，则消息恢复子系统会重发该消息。
         * 以此类推
         */
        List<Integer> timeInterval = Arrays.asList(4, 10, 30, 60, 120, 360);

        for (Integer timeout : timeInterval) {
            // 1. 查找消息状态为 1:已发送、未死亡状态，并且发送时间超过指定时间的消息
            //    计算发送时间止（当前时间 - 我们设定的超时分钟），如果消息的发送时间小于“发送时间止”，那么就说明超时了
            Date sendEndTime = DateUtil.offset(new Date(), DateField.MINUTE, timeout);
            List<Message> messages = messageMapper.selectTooLongUnConsumeMessageList(sendEndTime);

            // 2. 遍历消息，对未消费的消息进行重发
            for (Message message : messages) {
                // 添加重发次数
                message.setResendTimes(message.getResendTimes()+1);
                messageMapper.updateById(message);
                // 发送消息
                rabbitTemplate.convertAndSend("shop-account-topic-exchange", message.getConsumerQueue(), message);
            }
        }

        log.info("处理所有长时间未消费的消息结束 >>>>>>>>>>>>");
    }

    /**
     * 标记消息死亡，对于到达重发次数上限的消息设置为死亡
     */
    @Override
    public void updateMessageDead() {
        log.info("标记达到重发次数上限的消息为死亡状态开始 >>>>>>>>>>>>");

        /**
         * timeInterval 重发时间间隔
         * 举例： [4, 10, 30, 60, 120, 360]
         * 消息确认会下下游业务发送首次消息，4分钟内，下游业务没有确认消费该消息，则消息恢复子系统会重发该消息。
         * 再过10分钟（也就是消息确认后14分钟内），下游业务没有确认消费该消息，则消息恢复子系统会重发该消息。
         * 以此类推
         */
        List<Integer> timeInterval = Arrays.asList(4, 10, 30, 60, 120, 360);

        // 重发次数
        Integer resendTimes = timeInterval.size() - 1;
        Integer result = messageMapper.updateMessageDead(resendTimes);

        log.info("标记达到重发次数上限的消息为死亡状态结束 >>>>>>>>>>>>");
    }


    /**
     * 新增消息表
     * @param message
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer add(Message message) {
        message.setId(snowFlake.nextId());
        return messageMapper.insert(message);
    }

    /**
     * 根据Id查询消息表
     * @param id
     * @return com.shop.entity.Message
     */
    @Override
    public Message get(Long id) {
        return messageMapper.selectById(id);
    }

    /**
     * 分页查询消息表
     * @param message
     * @return java.util.List<com.shop.entity.Message>
     */
    @Override
    public PageInfo<List<Message>> findList(Message message, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.selectList(message);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据Id修改消息表
     * @param message
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer update(Message message) {
        return messageMapper.updateById(message);
    }

    /**
     * 根据Id删除消息表
     * @param id
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer delete(Long id) {
        return messageMapper.deleteById(id);
    }

}