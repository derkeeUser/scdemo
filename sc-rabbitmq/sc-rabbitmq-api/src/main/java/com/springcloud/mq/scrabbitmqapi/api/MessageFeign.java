package com.springcloud.mq.scrabbitmqapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 独舟
 * @description 消息表api接口
 * @version 2020-05-01
 */
@FeignClient(value = "sc-rabbitmq")
@RequestMapping(value = "/message-open-api")
public interface MessageFeign {

    /**
     * 添加预发送消息
     * @param consumerQueue 消费队列
     * @param messageBody   消息内容
     * @return 消息ID
     */
    @PostMapping(value = "/addWaitSendMessage")
    Long addWaitSendMessage(String consumerQueue, String messageBody);

    /**
     * 确认发送消息
     * @param messageId 消息 ID
     */
    @PostMapping(value = "/confirmAndSendMessage")
    void confirmAndSendMessage(Long messageId);

    /**
     * 根据Id删除消息表
     * @param id
     * @return java.lang.Integer
     */
    @PostMapping(value = "/delete")
    Integer delete(Long id);
}