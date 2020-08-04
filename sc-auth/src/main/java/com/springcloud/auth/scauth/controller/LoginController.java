package com.springcloud.auth.scauth.controller;

import com.springcloud.common.sccommon.enums.HttpStatusEnum;
import com.springcloud.common.sccommon.utils.JWTUtil;
import com.springcloud.common.sccommon.utils.UUIDUtil;
import com.springcloud.common.sccommon.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: scdemo
 * @description: 登录
 * @author: Chenxiaoming
 * @create: 2020-07-31 15:55:34
 */
@Slf4j
@RestController
@RequestMapping("/auth/")
public class LoginController {

    private final StringRedisTemplate redisTemplate;

    public LoginController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 登录认证
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("login")
    public CommonVO login(@RequestParam String username, @RequestParam String password) {
        try {
            if("admin".equals(username) && "admin".equals(password)){
                // 生成token
                String token = JWTUtil.generateToken(username);

                // 生成refreshToken
                String refreshToken = UUIDUtil.generateUUID();

                // 数据放入redis
                redisTemplate.opsForHash().put(refreshToken, "token", token);
                redisTemplate.opsForHash().put(refreshToken, "username", username);

                // 设置token的过期时间
                redisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

                // token数据封装
                Map<String, String> resultMap = new HashMap<>(2);
                resultMap.put("token", token);
                resultMap.put("refreshToken", refreshToken);

                return CommonVO.success(resultMap);
            }
            return CommonVO.error(HttpStatusEnum.UNAUTHORIZED.getCode(), "用户名或密码错误", "username or password error");
        } catch (Exception e) {
            log.error("登录过程中出现异常：{}", e.getMessage(), e);
            return CommonVO.error();
        }
    }

    /**
     * 刷新token
     */
    @GetMapping("/refreshToken")
    public CommonVO refreshToken(@RequestHeader String refreshToken) {
        try {
            String username = (String) redisTemplate.opsForHash().get(refreshToken, "username");
            if (StringUtils.isEmpty(username)) {
                return new CommonVO(HttpStatusEnum.UNAUTHORIZED);
            }

            // 生成新的token
            String newToken = JWTUtil.generateToken(username);
            redisTemplate.opsForHash().put(refreshToken, "token", newToken);

            // token数据封装
            Map<String, String> resultMap = new HashMap<>(2);
            resultMap.put("token", newToken);
            resultMap.put("refreshToken", refreshToken);

            return CommonVO.success(resultMap);
        } catch (Exception e) {
            log.error("token刷新异常：{}", e.getMessage(), e);
            return CommonVO.error();
        }
    }

    /**
     * @description: jwt在无状态下无法在服务器端注销，此处只是将token从redis中删除
     * @Author: Chenxiaoming
     * @params: [refreshToken]
     * @date: 2020/8/4 19:33
     * @return: com.springcloud.common.sccommon.vo.CommonVO
     */
    @GetMapping("logout")
    public CommonVO logout(@RequestHeader String refreshToken) {
        try {
            redisTemplate.delete(refreshToken);
            return CommonVO.success();
        } catch (Exception e) {
            log.error("账号注销异常：{}", e.getMessage(), e);
            return CommonVO.error();
        }
    }
}
