package com.SpringBoot.annotation;

import java.lang.annotation.*; 
 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethod {
    String value() default "";
    boolean trackTime() default true; // 是否记录耗时 
    Level level() default Level.INFO; // 日志级别 
    
    enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR 
    }
}