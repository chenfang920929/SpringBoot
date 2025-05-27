package com.SpringBoot.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Result {
    private int code;
    private String msg;
    private Object data;
    
    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(200); 
        result.setMsg(msg); 
        result.setData(data); 
        return result;
    }
    
    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(500); 
        result.setMsg(msg); 
        return result;
    }
    
    // getter和setter...
}