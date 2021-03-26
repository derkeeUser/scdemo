package com.springcloud.mq.scrabbitmqapi.api.fallback;


import com.springcloud.mq.scrabbitmqapi.api.MessageFeign;

/**
 * @program: scdemo
 * @description:
 * @author: chenxiaoming
 * @create: 2021-03-24 20:35:18
 */
public class MessageFeignImpl implements MessageFeign {
    /**
     * 添加预发送消息
     *
     * @param consumerQueue 消费队列
     * @param messageBody   消息内容
     * @return 消息ID
     */
    @Override
    public Long addWaitSendMessage(String consumerQueue, String messageBody) {
        return null;
    }

    /**
     * 确认发送消息
     *
     * @param messageId 消息 ID
     */
    @Override
    public void confirmAndSendMessage(Long messageId) {

    }

    /**
     * 根据Id删除消息表
     *
     * @param id
     * @return java.lang.Integer
     */
    @Override
    public Integer delete(Long id) {
        return null;
    }
}
