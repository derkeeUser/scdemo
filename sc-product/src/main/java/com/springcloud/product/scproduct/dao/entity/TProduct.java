package com.springcloud.product.scproduct.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TProduct)实体类
 *
 * @author makejava
 * @since 2020-04-27 11:17:40
 */
@Data
public class TProduct implements Serializable {
    private static final long serialVersionUID = 910029355240823543L;

    private Integer id;
    
    private String productName;
    
    private String productDescription;
    
    private Date createTime;
    
    private Date updateTime;

}