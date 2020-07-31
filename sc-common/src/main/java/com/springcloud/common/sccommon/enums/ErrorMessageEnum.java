package com.springcloud.common.sccommon.enums;

/**
 * @program: questionnaire
 * @description: 错误消息
 * @author: Chenxiaoming
 * @create: 2020-05-08 19:20:16
 */
public enum ErrorMessageEnum {
    /**
     * 错误消息
     */
    DATA_FIND_FAILURE("数据获取失败，请重试！","Data acquisition failed, please try again!"),
    PARAM_ERROR("参数有误，请重试！","Parameters error, please try again!"),
    INCORRECT_USERNAME_FORMAT("用户名格式错误！","incorrect username format!"),
    INCORRECT_JOBNUM_FORMAT("工号格式错误！","incorrect jobNum format!")
    ;

    private final String messageCN;
    private final String messageUS;

    ErrorMessageEnum(String messageCN, String messageUS) {
        this.messageCN = messageCN;
        this.messageUS = messageUS;
    }

    public String getMessageCN() {
        return messageCN;
    }

    public String getMessageUS() {
        return messageUS;
    }
}
