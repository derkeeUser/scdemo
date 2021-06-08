package com.chen.dubboproviderservice.service;

import com.alibaba.fastjson.JSONObject;
import com.chen.dubboproviderapi.api.UserRpcService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: scdemo
 * @description:
 * @author: chenxiaoming
 * @create: 2021-05-08 17:45:37
 */
@Service(version = "${dubbo.provider.UserRpcService.version}")
@Component
public class UserRpcServiceImpl implements UserRpcService {
    @Override
    public List<JSONObject> getUserList() {
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put(String.valueOf(i), "用户" + i + 1);
            result.add(json);
        }
        return result;
    }
}
