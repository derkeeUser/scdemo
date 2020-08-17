package com.springcloud.shiro.scshiro.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-08-13 14:23:32
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -14603422501778973L;
    /**
     * ID
     */
    private Long id;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    private String dataScope;

    private Integer level;

    private Set<Permission> permissions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(createTime, role.createTime) &&
                Objects.equals(name, role.name) &&
                Objects.equals(remark, role.remark) &&
                Objects.equals(dataScope, role.dataScope) &&
                Objects.equals(level, role.level) &&
                Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, name, remark, dataScope, level, permissions);
    }
}