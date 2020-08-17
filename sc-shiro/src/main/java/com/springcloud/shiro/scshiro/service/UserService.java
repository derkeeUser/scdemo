package com.springcloud.shiro.scshiro.service;

import com.springcloud.common.sccommon.vo.CommonVO;
import com.springcloud.shiro.scshiro.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-08-13 15:11:39
 */
public interface UserService {

    /**
     * 用户名查询
     * @param username
     * @return
     */
    User queryByUsername(String username, String password);

    /**
     * 登录
     * @param username
     * @param password
     */
    CommonVO login(String username, String password);

    /**
     * 注销
     * @return
     */
    CommonVO logout();

    /**
     * 获取用户信息，权限
     * @param username
     * @return
     */
    User getUserInfo(String username);
}