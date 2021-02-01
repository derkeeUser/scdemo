package com.springcloud.gateway.scgateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.common.sccommon.utils.JWTUtil;
import com.springcloud.common.sccommon.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @program: scdemo
 * @description: 全局过滤器
 * @author: Chenxiaoming
 * @create: 2020-07-31 16:30:52
 */
@Slf4j
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    /**
     * 排除过滤的 uri 地址
     */
    private static final String[] EXCLUDE_URL = {"/auth-server/auth/login", "/auth-server/auth/logout", "/auth-server/auth/refresh"};

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        log.info("url:{}", url);

        // 忽略以下url请求
        if (Arrays.asList(EXCLUDE_URL).contains(url)) {
            return chain.filter(exchange);
        }

        // 从请求头中取得token
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(token)){
            return setUnauthorizedResponse(exchange, "身份验证失败,请登录", "unauthorized");
        }

        // 请求中的token是否在redis中存在
        boolean verifyResult = JWTUtil.verify(token);
        if(!verifyResult){
            return setUnauthorizedResponse(exchange, "身份验证失败,token已失效", "invalid token");
        }

        return chain.filter(exchange);
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String zhMsg, String enMsg)
    {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        CommonVO vo = new CommonVO(HttpStatus.UNAUTHORIZED.value(), zhMsg, enMsg);
        DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(vo));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}