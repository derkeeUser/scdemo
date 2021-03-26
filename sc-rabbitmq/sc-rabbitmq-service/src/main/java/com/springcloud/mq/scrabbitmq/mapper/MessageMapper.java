package com.springcloud.mq.scrabbitmq.mapper;

import com.springcloud.mq.scrabbitmq.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 独舟
 * @description 消息表Dao接口
 * @version 2020-05-01
 */
@Mapper
public interface MessageMapper {

    /**
     * 插入消息表
     * @param message
     * @return java.lang.Integer
     */
    Integer insert(Message message);

    /**
     * 根据ID查询消息表
     * @param id
     * @return com.shop.entity.Message
     */
    Message selectById(@Param("id")Long id);

    /**
     * 列表查询消息表
     * @param message
     * @return java.util.List<com.shop.entity.Message>
     */
    List<Message> selectList(Message message);

    /**
     * 查询太长时间没有确定的消息
     * @param consumerQueue 队列
     * @param createEndTime 创建截止时间
     * @return java.util.List<com.shop.entity.Message>
     */
    List<Message> selectTooLongUnConfirmMessageList(@Param("consumerQueue") String consumerQueue, @Param("createEndTime") Date createEndTime);

    /**
     * 查询未死亡的并且太长时间没有消费的消息
     * @param sendEndTime 发送截止时间
     * @return java.util.List<com.shop.entity.Message>
     */
    List<Message> selectTooLongUnConsumeMessageList(@Param("sendEndTime") Date sendEndTime);

    /**
     * 根据ID修改消息表
     * @param message
     * @return java.lang.Integer
     */
    Integer updateById(Message message);

    /**
     * 根据重发次数设置消息为死亡状态
     * @param resendTimes 重发次数
     * @return java.lang.Integer
     */
    Integer updateMessageDead(@Param("resendTimes") Integer resendTimes);

    /**
     * 根据ID删除消息表
     * @param id
     * @return java.lang.Integer
     */
    Integer deleteById(@Param("id")Long id);
}