package com.springcloud.mq.scrabbitmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.mq.scrabbitmq.entity.Queue;
import com.springcloud.mq.scrabbitmq.mapper.QueueMapper;
import com.springcloud.mq.scrabbitmq.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueMapper queueMapper;

    /**
     * 新增消息队列
     * @param queue
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer add(Queue queue) {
        return queueMapper.insert(queue);
    }

    /**
     * 根据Id查询消息队列
     * @param id
     * @return com.shop.entity.Queue
     */
    @Override
    public Queue get(Long id) {
        return queueMapper.selectById(id);
    }

    /**
     * 分页查询消息队列
     * @param queue
     * @return java.util.List<com.shop.entity.Queue>
     */
    @Override
    public PageInfo<List<Queue>> findList(Queue queue, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Queue> list = queueMapper.selectList(queue);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据Id修改消息队列
     * @param queue
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer update(Queue queue) {
        return queueMapper.updateById(queue);
    }

    /**
     * 根据Id删除消息队列
     * @param id
     * @return java.lang.Integer
     */
    @Override
    @Transactional
    public Integer delete(Long id) {
        return queueMapper.deleteById(id);
    }

}