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
public class Salesback {

		private Integer id;
		
		private String orderId;

	    private String customerid;

	    private String paytype;

	    private Date salesbacktime;

	    private BigDecimal salebackprice;
	    
	    private String operateperson;

	    private Integer number;

	    private String remark;

	    private Integer goodsid;
	    
	    private String productcode;
	    
	    private Integer orderno;

	    private String goodsname;

	    private String size;
	    
	    private BigDecimal sum;    // 总价 = 单价 × 数量 
}
