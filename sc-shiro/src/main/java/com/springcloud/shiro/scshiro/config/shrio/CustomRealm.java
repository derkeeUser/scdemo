package com.springcloud.shiro.scshiro.config.shrio;

import com.springcloud.shiro.scshiro.entity.Permission;
import com.springcloud.shiro.scshiro.entity.Role;
import com.springcloud.shiro.scshiro.entity.User;
import com.springcloud.shiro.scshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: scdemo
 * @description: 权限信息验证
 * @author: xiaoming.Chan
 * @create: 2020-08-13 15:35:15
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("doGetAuthorizationInfo+"+principalCollection.toString());
        User user = userService.getUserInfo((String) principalCollection.getPrimaryPrincipal());

        // 把principals放session中 key=userId value=principals
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 赋予角色
        for(Role userRole:user.getRoles()){
            info.addRole(userRole.getName());
            // 赋予权限
            for (Permission permission : userRole.getPermissions()) {
                info.addStringPermission(permission.getName());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo +"  + authenticationToken.toString());

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        log.info(userName+token.getPassword());

        User user = userService.getUserInfo(token.getUsername());
        if (user != null) {
            // 设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            return new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        } else {
            return null;
        }
    }
}
