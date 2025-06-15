package com.SpringBoot.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Sales;
import com.SpringBoot.bean.Salesback;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.SalesService;
import com.SpringBoot.service.SalesbackService;

@RestController
@RequestMapping("salesback")
public class SalesbackController {
	
	@Autowired
	LayuiJson layuiJson;
	
	@Autowired
	SalesbackService salesbackService;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	HttpSession httpSession;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllSalesback")
	public LayuiJson<Salesback> loadAllSalesback(String customerid,Integer goodsid,String orderno,Integer page,Integer limit
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
		int index=(page-1)*limit;
		customerid="".equals(customerid)?null:customerid;
		orderno="".equals(orderno)?null:orderno;
		List<Salesback> data = salesbackService.select(customerid, goodsid,orderno,startTime,endTime,index, limit);
		Integer num=salesbackService.selectCount(customerid, goodsid,orderno,startTime,endTime);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
	}

	/**
     * 删除商品销售退回信息
     * @param id
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("deleteSalesback")
    public ResultObj deleteSalesback(Integer id){
        try {
            salesbackService.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @Transactional
    @RequestMapping("addSalesback")
    public ResultObj addSalesback(Integer id ,Integer number,String remark) {
    	
    	try {
    		Sales s = salesService.selectById(id);
    		Date salesbacktime=new Date();
    		String operateperson = (String) httpSession.getAttribute("username");
    		String customerid = s.getCustomerid();
    		String paytype = s.getPaytype();
    		BigDecimal salebackprice = s.getSaleprice();
    		Integer goodsid = s.getGoodsid();
    		String orderid =s.getOrderid();
    		
    		if(s.getNumber()==number) {
    			
    			salesbackService.insert(customerid, paytype, salesbacktime, salebackprice, operateperson, number, remark, goodsid,orderid);
    			salesService.delete(id);
    			
    	    	return ResultObj.BACKINPORT_SUCCESS;
    			
    		}else if(s.getNumber()>number) {
    			
    			salesbackService.insert(customerid, paytype, salesbacktime, salebackprice, operateperson, number, remark, goodsid,orderid);
    			salesService.updateNumber(id, s.getNumber()-number);
    	    	return ResultObj.BACKINPORT_SUCCESS;
    		}else {
    			
    			return ResultObj.BACKINPORT_ERROR;
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return ResultObj.BACKINPORT_ERROR;
    	
    }
	
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadAllOrders")
    public DataGridView loadAllOrders() {
    	List<String> list = salesbackService.selectOrders();
    	return new DataGridView(list!=null?list.size():0L,list);
    }
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadOrdersByCustomer")
    public DataGridView loadOrdersByCustomer(String customerid) {
    	List<String> list = salesbackService.selectByCustomer(customerid);
    	return new DataGridView(list!=null?list.size():0L,list);
    }
}
