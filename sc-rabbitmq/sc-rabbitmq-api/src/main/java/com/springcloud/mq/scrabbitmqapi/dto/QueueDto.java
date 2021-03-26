package com.springcloud.mq.scrabbitmqapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenxiaoming
 * @version 2021-03-24
 * @description 消息队列信息
 */
@Data
public class QueueDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String businessName;
    private String consumerQueue;
    /**
     * 消息确认URL，消息确认子系统与业务系统确认是否发送消息的接口地址
     */
    private String checkUrl;
    /**
     * 确认条件（毫秒），也就是多长时间未进行确认的消息需要进行确认
     */
    private Integer checkDuration;
    /**
     * 确认超时时长（毫秒），也就是HTTP请求超时时长
     */
    private Integer checkTimeout;

    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

}