package com.spring.accumulator.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * 通过反射获取连接点JoinPoint对应的方法信息
 *
 * @author wangrubin1
 * @date 2022-08-04
 */
public class AspectUtil {

    /**
     * 该解析器现程安全，可复用
     */
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 定义模板，花括号包裹变量
     */
    private static final TemplateParserContext PARSER_CONTEXT = new TemplateParserContext("{", "}");

    /**
     * 方法参数名称获取工具类
     */
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

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
        return Objects.requireNonNull(sra).getRequest();
    }

    /**
     * 用方法参数值替换占位符
     *
     * @param format    模板
     * @param joinPoint 连接点
     * @return 替换占位符之后的字符串
     */
    public static String formatLog(String format, JoinPoint joinPoint) {
        // 创建表达式
        Expression expression = PARSER.parseExpression(format, PARSER_CONTEXT);

        // 获取方法参数的名称和值
        String[] paramNames = NAME_DISCOVERER.getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] methodArgs = joinPoint.getArgs();
        Assert.isTrue(paramNames != null && paramNames.length == methodArgs.length, "方法参数不匹配");

        // 创建上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], methodArgs[i]);
        }

        return expression.getValue(context, String.class);
    }
}
