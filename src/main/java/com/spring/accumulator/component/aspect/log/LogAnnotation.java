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
     * 日志描述，默认为空
     */
    String description();
}
