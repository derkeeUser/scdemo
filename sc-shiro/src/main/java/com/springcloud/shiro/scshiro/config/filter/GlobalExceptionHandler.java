package com.springcloud.shiro.scshiro.config.filter;

import com.springcloud.common.sccommon.enums.HttpStatusEnum;
import com.springcloud.common.sccommon.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: scdemo
 * @description: 全局异常处理
 * @author: xiaoming.Chan
 * @create: 2020-08-13 16:22:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public CommonVO defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        log.error("异常", e);
        return CommonVO.error("异常! 错误位置:" + errorPosition);
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonVO httpRequestMethodHandler() {
        return CommonVO.error();
    }


    /**
     * 权限不足报错拦截
     */
    @ExceptionHandler(UnauthorizedException.class)
    public CommonVO unauthorizedExceptionHandler() {
        return CommonVO.error(HttpStatusEnum.UNAUTHORIZED);
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public CommonVO unauthenticatedException() {
        return CommonVO.error(HttpStatusEnum.NOT_ACCEPTABLE);
    }
}
