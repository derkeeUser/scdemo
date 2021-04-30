/*
package com.springcloud.common.sccommon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

*/
/**
 * @program: scdemo
 * @description: jwt工具类
 * @author: Chenxiaoming
 * @create: 2020-07-31 15:24:19
 *//*

@Slf4j
public class JWTUtil {

    public static final String SECRET_KEY = "123456";
    public static final long TOKEN_EXPIRE_TIME = 5 * 60 * 1000;
    */
/**
     * refreshToken过期时间
     *//*

    public static final long REFRESH_TOKEN_EXPIRE_TIME = 5 * 60 * 1000;
    */
/**
     * 签发人
     *//*

    private static final String ISSUER = "issuer";

    */
/**
     * @description: 生成签名
     * @Author: Chenxiaoming
     * @params: [username]
     * @date: 2020/7/31 15:25
     * @return: java.lang.String
     *//*

    public static String generateToken(String username) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                // 签发人
                .withIssuer(ISSUER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                // 保存身份标识
                .withClaim("username", username)
                .sign(algorithm);
    }

    */
/**
     * @description: 验证token
     * @Author: Chenxiaoming
     * @params: [token]
     * @date: 2020/7/31 15:27
     * @return: boolean
     *//*

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            log.error("token验证异常: {}",ex.getMessage());
        }
        return false;
    }

    */
/**
     * @description: 从token获取username
     * @Author: Chenxiaoming
     * @params: [token]
     * @date: 2020/7/31 15:28
     * @return: java.lang.String
     *//*

    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (Exception ex) {
            log.error("获取username异常: {}",ex.getMessage());
        }
        return "";
    }
}
*/
