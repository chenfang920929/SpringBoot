package com.SpringBoot.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Provider {

    private Integer id;

    private String providername;

    private String address;

    private String connectionperson;

    private String phone;
}
