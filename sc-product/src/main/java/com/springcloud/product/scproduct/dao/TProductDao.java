package com.springcloud.product.scproduct.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.product.scproduct.dao.entity.TProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TProduct)表数据库访问层
 *
 * @author xiaoming·Chen
 * @since 2020-04-27 11:20:17
 */
@Mapper
public interface TProductDao extends BaseMapper<TProduct> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(@Param("id") Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TProduct> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tProduct 实例对象
     * @return 对象列表
     */
    List<TProduct> queryAll(TProduct tProduct);
}