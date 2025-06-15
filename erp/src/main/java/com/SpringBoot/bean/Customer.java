package com.SpringBoot.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Customer {
	
    private String id;

    private String customername;

    private String address;

    private String connectionpersion;

    private String phone;

    private String email;
    
    private Integer available;
}
