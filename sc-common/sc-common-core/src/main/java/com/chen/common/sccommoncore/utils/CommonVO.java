package com.chen.common.sccommoncore.utils;

import com.chen.common.sccommoncore.enums.HttpStatusEnum;
import lombok.Data;

/**
 * @program: questionnaire
 * @description: 响应类
 * @author: Chenxiaoming
 * @create: 2020-05-08 18:42:37
 */
@Data
public class CommonVO {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 提示信息：中文
     */
    private String messageCN;
    /**
     * 提示信息：英文
     */
    private String messageUS;

    public CommonVO(Integer code, Object data, String messageCN, String messageUS) {
        this.code = code;
        this.data = data;
        this.messageCN = messageCN;
        this.messageUS = messageUS;
    }

    public CommonVO(Integer code, String messageCN, String messageUS) {
        this.code = code;
        this.messageCN = messageCN;
        this.messageUS = messageUS;
    }

    public CommonVO(HttpStatusEnum httpStatusEnum) {
        this.code = httpStatusEnum.getCode();
        this.messageCN = httpStatusEnum.getReasonPhraseCN();
        this.messageUS = httpStatusEnum.getReasonPhraseUS();
    }

    public CommonVO(Integer code, String messageCN) {
        this.code = code;
        this.messageCN = messageCN;
    }

    public static CommonVO success(Object data) {
        return new CommonVO(HttpStatusEnum.OK.getCode(), data, HttpStatusEnum.OK.getReasonPhraseCN(), HttpStatusEnum.OK.getReasonPhraseUS());
    }

    public static CommonVO success() {
        return success(null);
    }

    public static CommonVO error(Integer code, String messageCN, String messageUS) {
        return new CommonVO(code, messageCN, messageUS);
    }

    public static CommonVO error(HttpStatusEnum httpStatusEnum) {
        return new CommonVO(httpStatusEnum);
    }

    public static CommonVO error() {
        return error(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    public static CommonVO error(HttpStatusEnum statusEnum, String msg) {
        return new CommonVO(statusEnum.getCode(), msg);
    }

    public static CommonVO error(String msg) {
        return new CommonVO(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(), msg);
    }
}
