package com.spring.accumulator.component.aspect.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识切入点对应方法的注解
 *
 * @author wangrubin1
 * @date 2022-08-04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 日志类型，必填
     */
    EnumLogType type();

    /**
     * 操作日志
     */
    String operateLog();

    /**
     * 操作日志中是否包含形参
     */
    boolean containsParam() default false;
}
