package com.chen.dubboproviderapi.api;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @program: scdemo
 * @description: rpc调用
 * @author: chenxiaoming
 * @create: 2021-05-08 18:14:59
 */
public interface UserRpcService {

    List<JSONObject> getUserList();
}
