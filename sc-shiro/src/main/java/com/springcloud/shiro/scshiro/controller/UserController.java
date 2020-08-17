package com.springcloud.shiro.scshiro.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.common.sccommon.vo.CommonVO;
import com.springcloud.shiro.scshiro.config.util.CommonUtil;
import com.springcloud.shiro.scshiro.entity.User;
import com.springcloud.shiro.scshiro.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-08-13 15:11:43
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public CommonVO login(@RequestBody User user) {
        CommonUtil.hasAllRequired((JSONObject) JSON.toJSON(user), "username,password");
        return userService.login(user.getUsername(), user.getPassword());
    }

    // 没有此注解时默认不验证身份
    @RequiresPermissions("USER_SELECT")
    @GetMapping("/index")
    public String index() {
        return "index!";
    }

    @GetMapping("/index2")
    public String index2() {
        return "index2!";
    }

    @GetMapping("logout")
    public CommonVO logout() {
        return userService.logout();
    }
}