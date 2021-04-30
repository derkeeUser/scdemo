package com.springcloud.mq.scrabbitmq.controller;

import com.springcloud.mq.scrabbitmq.entity.Queue;
import com.springcloud.mq.scrabbitmq.service.QueueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 独舟
 * @description 消息队列api接口
 * @version 2020-05-01
 */
@RestController
@Api(value = "消息队列接口", tags = "消息队列")
@RequiredArgsConstructor
@RequestMapping(value = "/queue")
public class QueueController {

    private final QueueService queueService;

    @ApiOperation(value = "新增消息队列")
    @PostMapping(value = "/add")
    public CommonVO add(Queue queue){
        queueService.add(queue);
        return CommonVO.success();
    }

    @ApiOperation(value = "根据Id查询消息队列详情")
    @PostMapping(value = "/get")
    public CommonVO get(Long id){
        return CommonVO.success(queueService.get(id));
    }

    @ApiOperation(value = "分页查询消息队列")
    @PostMapping(value = "/findList")
    public CommonVO findList(@RequestBody Queue queue,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return CommonVO.success(queueService.findList(queue, pageNum, pageSize));
    }

    @ApiOperation(value = "根据Id修改消息队列")
    @PostMapping(value = "update")
    public CommonVO update(@RequestBody Queue queue){
        queueService.update(queue);
        return CommonVO.success();
    }

    @ApiOperation(value = "删除消息队列")
    @PostMapping(value = "/delete")
    public CommonVO delete(Long id){
        queueService.delete(id);
        return CommonVO.success();
    }

}