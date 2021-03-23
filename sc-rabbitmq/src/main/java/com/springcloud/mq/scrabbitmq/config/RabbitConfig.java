package com.springcloud.mq.scrabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author duzhou
 */
@Configuration
public class RabbitConfig {

    /**
     * 支付订单消息队列（接收上游应用发送过来的消息，消息为未支付状态的订单的消息）
     * 创建队列的4个参数（顺序也也一样）：
     *     name：        队列名字
     *     durable：     是否持久化
     *     exclusive：   是否排他
     *     autoDelete：  是否自动删除
     *
     * durable:是否持久化，默认是false，持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     * exclusive:是否排他，默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     *
     * @return
     */
    @Bean
    public Queue payOrderQueue() {
        return new Queue("rb-demo.transfer.queue",true, false,  false );
    }

    /**
     * 创建交换机
     * 这里是否该使用 Topic 交换机，或许使用直连交换机或许会好一些？？？
     * 参数说明：
     *     name：        交换机名字
     *     durable：     是否持久化
     *     autoDelete：  是否自动删除
     * @return
     */
    @Bean
    public TopicExchange payOrderTopicExchange() {
        return new TopicExchange("rb-demo-topic-exchange", true, false);
    }

    /**
     * 创建绑定
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(payOrderQueue()).to(payOrderTopicExchange()).with("rb-demo.transfer.queue");
    }



    /**
     * 消息确认回调函数
     *
     * 推送消息存在四种情况：
     * 1.消息推送到sever，交换机和队列啥都没找到
     * 2.消息推送到server，但是在server里找不到交换机
     * 3.消息推送到server，找到交换机了，但是没找到队列
     * 4.消息推送成功
     *
     * 情况 1/2/4 会进入 confirm 回调函数
     * 情况3两个回调函数都会进入
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }
        });

        return rabbitTemplate;
    }

}
