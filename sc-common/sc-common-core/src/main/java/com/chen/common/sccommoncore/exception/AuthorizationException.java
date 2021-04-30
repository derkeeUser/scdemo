package com.chen.common.sccommoncore.exception;

/**
 * @program: questionnaire
 * @description: 身份验证自定义异常
 * @author: Chenxiaoming
 * @create: 2020-07-07 09:22:02
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    protected AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
