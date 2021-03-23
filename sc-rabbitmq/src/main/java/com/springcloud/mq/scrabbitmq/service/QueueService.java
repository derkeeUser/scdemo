package com.springcloud.mq.scrabbitmq.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.mq.scrabbitmq.entity.Queue;

import java.util.List;

/**
 * @author  独舟
 * @description 消息队列业务逻辑接口
 * @date 2020-05-01
 */
public interface QueueService {

    /**
     * 新增消息队列
     * @param queue
     * @return java.lang.Integer
     */
    Integer add(Queue queue);

    /**
     * 根据Id查询消息队列
     * @param id
     * @return com.shop.entity.Queue
     */
    Queue get(Long id);

    /**
     * 列表查询消息队列
     * @param queue
     * @return java.util.List<com.shop.entity.Queue>
     */
    PageInfo<List<Queue>> findList(Queue queue, Integer pageNum, Integer pageSize);

    /**
     * 根据Id修改消息队列
     * @param queue
     * @return java.lang.Integer
     */
    Integer update(Queue queue);

    /**
     * 根据Id删除消息队列
     * @param id
     * @return java.lang.Integer
     */
    Integer delete(Long id);

}