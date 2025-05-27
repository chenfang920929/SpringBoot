package com.SpringBoot.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ImportError {
    private int row;
    private String message;
    
    // 构造方法、getter和setter...
}