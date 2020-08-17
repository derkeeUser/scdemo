package com.springcloud.shiro.scshiro.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-08-13 14:47:25
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -57175907705873714L;
    /**
     * ID
     */
    private Long id;
    /**
     * 别名
     */
    private String alias;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 名称
     */
    private String name;
    /**
     * 上级权限
     */
    private Integer pid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(name, that.name) &&
                Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias, createTime, name, pid);
    }
}