package com.SpringBoot.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.Around; 
import org.aspectj.lang.annotation.Aspect; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Component;

import com.SpringBoot.annotation.LogMethod; 
 
@Aspect 
@Component 
public class MethodLogAspect {
    
    @Around("@annotation(logMethod)")
    public Object logMethodExecution(ProceedingJoinPoint pjp, LogMethod logMethod) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass()); 
        String methodName = pjp.getSignature().toShortString(); 
        Object[] args = pjp.getArgs(); 
        
        // 1. 方法进入日志 
        logAtLevel(log, logMethod.level(),  
            "【{}】方法调用开始 | 参数: {}", methodName, formatArgs(args));
        
        long startTime = System.currentTimeMillis(); 
        try {
            Object result = pjp.proceed(); 
            
            // 2. 方法退出日志（成功）
            if (logMethod.trackTime())  {
                long duration = System.currentTimeMillis()  - startTime;
                logAtLevel(log, logMethod.level(), 
                    "【{}】方法执行成功 | 耗时: {}ms | 结果: {}", 
                    methodName, duration, formatResult(result));
            }
            return result;
            
        } catch (Exception e) {
            // 3. 方法退出日志（异常）
            log.error(" 【{}】方法执行异常 | 错误: {}", methodName, e.getMessage(),  e);
            throw e;
        }
    }
    
    private void logAtLevel(Logger log, LogMethod.Level level, String format, Object... args) {
        switch (level) {
            case TRACE : log.trace(format,  args);
            case DEBUG : log.debug(format,  args);
            case INFO : log.info(format,  args);
            case WARN : log.warn(format,  args);
            case ERROR : log.error(format,  args);
        }
    }
    
    private String formatArgs(Object[] args) {
        // 实现参数格式化逻辑（可过滤敏感信息）
        return Arrays.toString(args); 
    }
    
    private String formatResult(Object result) {
        // 实现结果格式化逻辑 
        return result != null ? result.toString()  : "null";
    }
}