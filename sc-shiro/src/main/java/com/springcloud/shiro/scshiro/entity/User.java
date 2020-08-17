package com.springcloud.shiro.scshiro.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-08-13 14:46:55
 */
public class User implements Serializable {
    private static final long serialVersionUID = 579171813573239382L;
    /**
     * ID
     */
    private Long id;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 最后修改密码的日期
     */
    private Date lastPasswordResetTime;

    private Long deptId;

    private String phone;

    private Long jobId;

    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    public void setLastPasswordResetTime(Date lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}