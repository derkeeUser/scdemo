package com.springcloud.mq.scrabbitmq.mapper;

import com.springcloud.mq.scrabbitmq.entity.Queue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 独舟
 * @description 消息队列Dao接口
 * @version 2020-05-01
 */
@Mapper
public interface QueueMapper {

    /**
     * 插入消息队列
     * @param queue
     * @return java.lang.Integer
     */
    Integer insert(Queue queue);

    /**
     * 根据ID查询消息队列
     * @param id
     * @return com.shop.entity.Queue
     */
    Queue selectById(@Param("id") Long id);

    /**
     * 列表查询消息队列
     * @param queue
     * @return java.util.List<com.shop.entity.Queue>
     */
    List<Queue> selectList(Queue queue);

    /**
     * 根据ID修改消息队列
     * @param queue
     * @return java.lang.Integer
     */
    Integer updateById(Queue queue);

    /**
     * 根据ID删除消息队列
     * @param id
     * @return java.lang.Integer
     */
    Integer deleteById(@Param("id") Long id);

}