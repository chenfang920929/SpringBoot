package com.SpringBoot.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Sales {

	 	private Integer id;
	 	
	 	private String orderid;

	    private String customerid;

	    private String paytype;

	    private Date salestime;

	    private String operateperson;

	    private Integer number;

	    private String remark;

	    private BigDecimal saleprice;
	    
	    private Integer goodsid;
	    
	    private String productcode;

	    private String customername;

	    private String goodsname;

	    private String size;
}
