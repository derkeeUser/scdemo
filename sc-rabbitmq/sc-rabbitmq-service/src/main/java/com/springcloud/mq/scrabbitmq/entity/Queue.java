package com.springcloud.mq.scrabbitmq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 独舟
 * @description 消息队列信息
 * @version 2020-05-01
 */
@Data
@ApiModel(value = "消息队列信息")
public class Queue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "充值记录ID，最大长度20", required = true)
	private Long id;
    @ApiModelProperty(value = "微服务名称，最大长度200", required = false)
	private String businessName;
    @ApiModelProperty(value = "消费队列，最大长度20", required = false)
	private String consumerQueue;
    /**
     * 消息确认URL，消息确认子系统与业务系统确认是否发送消息的接口地址
     */
    @ApiModelProperty(value = "消息确认URL，最大长度500", required = false)
	private String checkUrl;
    /**
     * 确认条件（毫秒），也就是多长时间未进行确认的消息需要进行确认
     */
    @ApiModelProperty(value = "确认条件（毫秒），最大长度11", required = false)
	private Integer checkDuration;
    /**
     * 确认超时时长（毫秒），也就是HTTP请求超时时长
     */
    @ApiModelProperty(value = "确认超时时长（毫秒），最大长度11", required = false)
	private Integer checkTimeout;

    @ApiModelProperty(value = "创建者，最大长度200", required = false)
	private String createUser;
    @ApiModelProperty(value = "创建时间，最大长度0", required = false)
	private Date createTime;
    @ApiModelProperty(value = "更新者，最大长度200", required = false)
	private String updateUser;
    @ApiModelProperty(value = "更新时间，最大长度0", required = false)
	private Date updateTime;

}