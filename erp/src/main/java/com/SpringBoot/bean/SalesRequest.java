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
public class SalesRequest {
    private String orderid;
    private String customerid;
    private List<SalesItem> products;
}