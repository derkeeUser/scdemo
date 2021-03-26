package com.springcloud.mq.scrabbitmqapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenxiaoming
 * @version 2021-03-24
 * @description 消息表信息
 */
@Data
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String consumerQueue;
    private String messageBody;
    private Integer resendTimes;
    private Integer alreadyDead;
    private Integer status;
    private Date confirmTime;
    private Date createTime;
    private Date updateTime;

}