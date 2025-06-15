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
    
    private String description;
    
    private String providername;
    
    private Integer providerid;
    
    private Integer number;
    
    private Integer in_count;
    
    private Integer out_count;
    
    private Integer actual_count;
}
