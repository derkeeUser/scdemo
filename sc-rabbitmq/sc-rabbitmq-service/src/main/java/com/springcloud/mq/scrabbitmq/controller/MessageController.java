package com.springcloud.mq.scrabbitmq.controller;

import com.springcloud.mq.scrabbitmq.entity.Message;
import com.springcloud.mq.scrabbitmq.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 独舟
 * @description 消息表api接口
 * @version 2020-05-01
 */
@RestController
@Api(value = "消息表接口", tags = "消息表")
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController {

    private final MessageService messageService;

    @ApiOperation(value = "新增消息表")
    @PostMapping(value = "/add")
    public CommonVO add(@RequestBody Message message){
        messageService.add(message);
        return CommonVO.success();
    }

    @ApiOperation(value = "根据Id查询消息表详情")
    @GetMapping(value = "/get")
    public CommonVO get(Long id){
        return CommonVO.success(messageService.get(id));
    }

    @ApiOperation(value = "分页查询消息表")
    @PostMapping(value = "/findList")
    public CommonVO findList(@RequestBody Message message,
                                                    @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return CommonVO.success(messageService.findList(message, pageNum, pageSize));
    }

    @ApiOperation(value = "根据Id修改消息表")
    @PostMapping(value = "update")
    public CommonVO update(@RequestBody Message message){
        messageService.update(message);
        return CommonVO.success();
    }

    @ApiOperation(value = "删除消息表")
    @GetMapping(value = "/delete")
    public CommonVO delete(Long id){
        messageService.delete(id);
        return CommonVO.success();
    }

}