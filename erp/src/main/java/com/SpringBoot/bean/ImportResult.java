package com.SpringBoot.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ImportResult {
    private int total;
    private int successCount;
    private int failureCount;
    private List<ImportError> errorMessages;
    
    // 构造方法、getter和setter...
}