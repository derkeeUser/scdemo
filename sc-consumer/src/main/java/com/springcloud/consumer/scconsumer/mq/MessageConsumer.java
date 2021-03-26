package com.springcloud.consumer.scconsumer.mq;

import com.rabbitmq.client.Channel;
import com.springcloud.mq.scrabbitmqapi.api.MessageFeign;
import com.springcloud.mq.scrabbitmqapi.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 * @description: mq消费
 * @author chenxiaoming
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class MessageConsumer {

    private final MessageFeign messageService;

    /**
     * @RabbitListener 监听的队列的配置
     * @RabbitListener 还可以帮我们自动在 RabbitMQ 服务器创建队列和交换机、Channel、并且绑定好
     *  durable：是否持久化
     *
     *  @Payload 消息体
     *  @Headers 消息头
     *
     * @RabbitHandler 可以传个 OrderMessage 过来（对方发什么对象过来，我就用什么对象接受？）
     * 使用了手动的接受方式（acknowledge-mode:manual），必须要指定 Channel
     * 消息包含两部分内容，一部分是消息体内容，还有一部分是消息头内容，所以使用Map接收
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "shop-account.transfer.queue", durable = "true"),
            exchange = @Exchange(name = "shop-account-topic-exchange", durable = "true", type = "topic"),
            key = "shop-account.transfer.queue"
    ))
    @RabbitHandler
    public void onPayOrderMessage(@Payload MessageDto message, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        try {

            /*// 1. 解析消息数据
            Account account = new Gson().fromJson(message.getMessageBody(), Account.class);

            // 2. 幂等校验，以消息id作为事务去重唯一索引，往去重表插入数据
            Integer count = deDuplicationDao.insert(account.getTxId());

            // 插入成功，才能继续进行业务处理
            if (count > 0) {
                // 充值成功业务处理
                accountService.addMoney(account.getId(), account.getMoney());
            }

            // 删除 MQ 服务的消息
            Long messageId = message.getId();
            messageService.delete(messageId);

            // ACK（手工签收）
            // 如果是手动消费（acknowledge-mode: manual）的情况下将下面的语句注释掉，那么队列里面的消息就不会删除
            Long delveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(delveryTag,false);*/

        } catch (Exception ex) {
            log.info("消费异常：{}", ex);
        }

    }
}
