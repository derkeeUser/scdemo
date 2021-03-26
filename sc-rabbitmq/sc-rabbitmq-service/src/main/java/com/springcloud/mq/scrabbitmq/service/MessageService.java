package com.springcloud.mq.scrabbitmq.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.mq.scrabbitmq.entity.Message;

import java.util.List;

/**
 * @author  独舟
 * @description 消息表业务逻辑接口
 * @date 2020-05-01
 */
public interface MessageService {

    /**
     * 添加预发送消息
     * @param consumerQueue 消费队列
     * @param messageBody   消息内容
     * @return
     */
    Long addWaitSendMessage(String consumerQueue, String messageBody);

    /**
     * 确认并发送消息
     * @param messageId 消息id
     */
    void confirmAndSendMessage(Long messageId);

    /**
     * 消息确认功能，处理所有长时间未确认的消息，和上游业务系统确认是否发送该消息
     */
    void checkWaitingMessage();

    /**
     * 消息恢复功能，对于长时间未消费的消息，重新发送消息给下层业务系统
     */
    void checkUnConsumeMessage();

    /**
     * 标记消息死亡，对于到达重发次数上限的消息设置为死亡
     */
    void updateMessageDead();


    /**
     * 新增消息表
     * @param message
     * @return java.lang.Integer
     */
    Integer add(Message message);

    /**
     * 根据Id查询消息表
     * @param id
     * @return com.shop.entity.Message
     */
    Message get(Long id);

    /**
     * 列表查询消息表
     * @param message
     * @return java.util.List<com.shop.entity.Message>
     */
    PageInfo<List<Message>> findList(Message message, Integer pageNum, Integer pageSize);

    /**
     * 根据Id修改消息表
     * @param message
     * @return java.lang.Integer
     */
    Integer update(Message message);

    /**
     * 根据Id删除消息表
     * @param id
     * @return java.lang.Integer
     */
    Integer delete(Long id);
}