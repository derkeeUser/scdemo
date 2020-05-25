package com.springcloud.product.scproduct.service;

import com.springcloud.product.scproduct.dao.entity.TProduct;

import java.util.List;

/**
 * (TProduct)表服务接口
 *
 * @author xiaoming·Chen
 * @since 2020-04-27 11:20:18
 */
public interface TProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProduct> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tProduct 实例对象
     * @return 实例对象
     */
    TProduct insert(TProduct tProduct);

    /**
     * 修改数据
     *
     * @param tProduct 实例对象
     * @return 实例对象
     */
    TProduct update(TProduct tProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}