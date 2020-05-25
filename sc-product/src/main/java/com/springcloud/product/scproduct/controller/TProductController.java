package com.springcloud.product.scproduct.controller;

import com.springcloud.product.scproduct.dao.entity.TProduct;
import com.springcloud.product.scproduct.service.TProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * (TProduct)表控制层
 *
 * @author xiaoming·Chen
 * @since 2020-04-27 11:20:19
 */
@Slf4j
@RestController
@RequestMapping("/product")
@RefreshScope
public class TProductController {

    @Value("${user.username:cxm}")
    private String username;
    @Value("${user.password:123}")
    private String password;
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

    @GetMapping("/get/value")
    public Object getValue() {
        Map<String, String> result = new HashMap<>();
        result.put("username", username);
        result.put("password", password);
        return result;
    }

}