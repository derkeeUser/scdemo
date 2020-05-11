package com.springcloud.product.scproduct.controller;

import com.springcloud.entity.scentity.product.TProduct;
import com.springcloud.product.scproduct.service.TProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (TProduct)表控制层
 *
 * @author xiaoming·Chen
 * @since 2020-04-27 11:20:19
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class TProductController {
    /**
     * 服务对象
     */
    private final TProductService tProductService;

    @Autowired
    public TProductController(TProductService tProductService) {
        this.tProductService = tProductService;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TProduct selectOne(Integer id) {
        try {
            log.info("id:{}", id);
            return this.tProductService.queryById(id);
        } catch (Exception e) {
            log.error("获取数据异常：{}", e.getMessage(), e);
            return null;
        }
    }

}