package com.springcloud.gateway.scgateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.common.sccommon.utils.JWTUtil;
import com.springcloud.common.sccommon.vo.CommonVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @program: scdemo
 * @description: 全局过滤器
 * @author: Chenxiaoming
 * @create: 2020-07-31 16:30:52
 */
@Component
public class JWTAuthFilter implements GlobalFilter, Ordered {

    private static final String EXCLUDE_URL = "/auth-server/";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        // 忽略以下url请求
        if(url.contains(EXCLUDE_URL)){
            return chain.filter(exchange);
        }

        // 从请求头中取得token
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(token)){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            CommonVO vo = new CommonVO(401, "身份验证失败", "unauthorized");
            DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(vo));
            return response.writeWith(Flux.just(buffer));
        }

        // 请求中的token是否在redis中存在
        boolean verifyResult = JWTUtil.verify(token);
        if(!verifyResult){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            CommonVO vo = new CommonVO(401, "token已失效", "invalid token");
            DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(vo));
            return response.writeWith(Flux.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
