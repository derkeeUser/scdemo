package com.springcloud.shiro.scshiro.service.impl;

import com.springcloud.common.sccommon.vo.CommonVO;
import com.springcloud.shiro.scshiro.dao.PermissionDao;
import com.springcloud.shiro.scshiro.dao.RoleDao;
import com.springcloud.shiro.scshiro.dao.UserDao;
import com.springcloud.shiro.scshiro.entity.Permission;
import com.springcloud.shiro.scshiro.entity.Role;
import com.springcloud.shiro.scshiro.entity.User;
import com.springcloud.shiro.scshiro.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-08-13 15:11:40
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PermissionDao permissionDao;

    @Override
    public User queryByUsername(String username, String password) {
        User user = userDao.queryByUsername(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public CommonVO login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return CommonVO.success();
        } catch (AuthenticationException e) {
            log.error("登录异常：{}", e.getMessage(), e);
            return CommonVO.error("登录异常" + e.getMessage());
        }
    }

    /**
     * 退出登录
     */
    @Override
    public CommonVO logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return CommonVO.success();
        } catch (Exception e) {
            log.error("注销失败：{}", e.getMessage(), e);
            return CommonVO.error();
        }
    }

    @Override
    public User getUserInfo(String username) {
        User user = userDao.queryByUsername(username);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleDao.queryByUserId(user.getId().intValue());
        for (Role role : roles) {
            List<Permission> permissions = permissionDao.queryByRoleId(role.getId().intValue());
            role.setPermissions(new HashSet<>(permissions));
        }
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}