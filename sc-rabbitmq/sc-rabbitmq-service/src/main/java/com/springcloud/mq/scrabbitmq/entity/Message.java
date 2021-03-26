package com.springcloud.mq.scrabbitmq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 独舟
 * @description 消息表信息
 * @version 2020-05-01
 */
@Data
@ApiModel(value = "消息表信息")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "充值记录ID，最大长度20", required = true)
	private Long id;
    @ApiModelProperty(value = "消费队列，最大长度20", required = false)
	private String consumerQueue;
    @ApiModelProperty(value = "消息内容，最大长度0", required = false)
    private String messageBody;
    @ApiModelProperty(value = "重发次数，最大长度11", required = false)
	private Integer resendTimes;
    @ApiModelProperty(value = "是否已死（0:否、1:是），最大长度11", required = false)
	private Integer alreadyDead;
    @ApiModelProperty(value = "消息状态（0:未发送 1:已发送），最大长度2", required = false)
	private Integer status;
    @ApiModelProperty(value = "确认时间，最大长度0", required = false)
	private Date confirmTime;
    @ApiModelProperty(value = "创建时间，最大长度0", required = false)
	private Date createTime;
    @ApiModelProperty(value = "更新时间，最大长度0", required = false)
	private Date updateTime;

}