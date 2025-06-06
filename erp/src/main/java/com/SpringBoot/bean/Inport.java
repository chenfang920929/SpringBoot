package com.SpringBoot.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Inport {

	private Integer id;

    private Date inporttime;

    private String operateperson;

    private Integer number;

    private String remark;

    private Double inportprice;

    private Integer providerid;
    
    private String productcode;

    private Integer goodsid;

    /**
     * 供应商姓名
     */
    private String providername;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 商品规格
     */
    private String size;

	
}
