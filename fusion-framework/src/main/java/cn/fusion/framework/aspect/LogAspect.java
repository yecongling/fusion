package cn.fusion.framework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @ClassName LogAspect
 * @Description 基于AOP记录用户操作日志
 * @Author ycl
 * @Date 2025/3/5 14:24
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 记录用户操作日志（写了这个注解的都会有）
     *
     * @param joinPoint 连接点
     * @return 结果
     * @throws Throwable 异常
     */
    @Around("@annotation(cn.fusion.framework.annotation.LogExecution)")
    public Object execution(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录日志
        log.info("用户操作日志记录");
        return joinPoint.proceed();
    }
}
