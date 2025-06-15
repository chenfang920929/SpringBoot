package com.SpringBoot.bean;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SalesItem {


	    private Integer goodsid;
	    	    
	    private Integer number;
	    
	    private BigDecimal saleprice;
	    
		private String paytype;
	    
	    private String remark;
}
