package com.spring.accumulator.component.aspect.log;

import com.alibaba.excel.util.StringUtils;
import com.spring.accumulator.common.response.ResponseResult;
import com.spring.accumulator.component.aspect.AspectUtil;
import com.spring.accumulator.dao.OperateLogMapper;
import com.spring.accumulator.entity.OperateLogPO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 操作日志记录切面
 *
 * @author wangrubin
 * @date 2022-08-04
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "operate.log.enable", havingValue = "true", matchIfMissing = true)
public class OperateLogAspect {

    @Resource
    private OperateLogMapper logMapper;

    /**
     * 定义切入点：有一组带有LogAnnotation注解的方法组成
     */
    @Pointcut("@annotation(com.spring.accumulator.component.aspect.log.LogAnnotation)")
    public void logPointCut() {
    }

    /**
     * 返回后通知：记录操作日志
     *
     * @param joinPoint 连接点，包含切入点绑定的一个方法的信息：签名和参数
     * @param result    连接点方法返回值
     */
    @AfterReturning(value = "logPointCut()", returning = "result")
    public void recordLog(JoinPoint joinPoint, Object result) {
        try {
            LogAnnotation logAnnotation = AspectUtil.getMethodAnnotation(joinPoint, LogAnnotation.class);

            Integer operateType = logAnnotation.type().getCode();
            StringBuilder logBuilder = new StringBuilder();
            if (logAnnotation.containsParam()) {
                logBuilder.append(AspectUtil.formatLog(logAnnotation.operateLog(), joinPoint));
            } else {
                logBuilder.append(logAnnotation.operateLog());
            }
            if (result instanceof ResponseResult) {
                ResponseResult<?> responseResult = (ResponseResult<?>) result;
                if (StringUtils.isNotBlank(responseResult.getBusinessLog())) {
                    logBuilder.append(":").append(responseResult.getBusinessLog());
                }
            }

            // TODO: 等权限体系建立之后要记录操作用户
            OperateLogPO logPO = new OperateLogPO();
            logPO.setOperate(operateType);
            logPO.setLog(logBuilder.toString());
            logMapper.insert(logPO);
            log.info("写入操作日志成功,日志ID:{}", logPO.getId());
        } catch (Exception e) {
            // 写入操作日志的错误不要抛出，因为连接点方法已经正确执行并返回了
            log.error("写入操作日志失败", e);
        }
    }
}
