package com.spring.accumulator.common.exception;

import com.spring.accumulator.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author wangrubin
 * @date 2022-08-02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局处理异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<?> exceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {
        // 重置response
        String requestUri = req.getRequestURI();
        String message = String.format("请求{%s}抛出异常:%s", requestUri, e.getCause());
        log.error(message, e);
        return ResponseResult.fail(e.getMessage());
    }
}