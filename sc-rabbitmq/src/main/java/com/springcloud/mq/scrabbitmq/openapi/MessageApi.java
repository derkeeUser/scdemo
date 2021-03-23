package com.springcloud.mq.scrabbitmq.openapi;

import com.springcloud.mq.scrabbitmq.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 独舟
 * @description 消息表api接口
 * @version 2020-05-01
 */
@ApiIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message-open-api")
public class MessageApi {

    private final MessageService messageService;

    /**
     * 添加预发送消息
     * @param consumerQueue 消费队列
     * @param messageBody   消息内容
     * @return 消息ID
     */
    @PostMapping(value = "/addWaitSendMessage")
    public Long addWaitSendMessage(String consumerQueue, String messageBody){
        return messageService.addWaitSendMessage(consumerQueue, messageBody);
    }

    /**
     * 确认发送消息
     * @param messageId 消息 ID
     */
    @PostMapping(value = "/confirmAndSendMessage")
    public void confirmAndSendMessage(Long messageId){
        messageService.confirmAndSendMessage(messageId);
    }

    /**
     * 根据Id删除消息表
     * @param id
     * @return java.lang.Integer
     */
    @PostMapping(value = "/delete")
    public Integer delete(Long id) {
        return messageService.delete(id);
    }
}