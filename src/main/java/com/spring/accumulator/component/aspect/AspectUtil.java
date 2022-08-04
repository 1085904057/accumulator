package com.spring.accumulator.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * 通过反射获取连接点JoinPoint对应的方法信息
 *
 * @author wangrubin1
 * @date 2022-08-04
 */
public class AspectUtil {
    /**
     * 从JoinPoint中获取方法注解
     *
     * @param joinPoint 连接点
     * @param clazz     注解类
     * @param <T>       类型
     * @return 注解对象
     */
    public static <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> clazz) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(clazz);
    }

    /**
     * 获取当前请求
     *
     * @return request
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
