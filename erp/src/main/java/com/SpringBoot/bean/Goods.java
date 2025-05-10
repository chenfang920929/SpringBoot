package com.SpringBoot.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Goods {

	private Integer id;

    private String goodsname;

    private String size;

    private String productcode;
    
    private String providername;
    
    private Integer number;
}
