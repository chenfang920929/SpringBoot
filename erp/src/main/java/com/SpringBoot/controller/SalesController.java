package com.SpringBoot.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Goods;
import com.SpringBoot.bean.Sales;
import com.SpringBoot.bean.SalesItem;
import com.SpringBoot.bean.SalesRequest;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.GoodsService;
import com.SpringBoot.service.SalesService;

@RestController
@RequestMapping("sales")
public class SalesController {
	
	@Autowired
	LayuiJson layuiJson;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	GoodsService goodsService;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllSales")
	public LayuiJson<Sales> loadAllSales(String customerid,Integer goodsid,String orderid,Integer page,Integer limit
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
		int index=(page-1)*limit;
		customerid="".equals(customerid)?null:customerid;
		orderid="".equals(orderid)?null:orderid;
		List<Sales> data = salesService.select(customerid, goodsid, orderid,startTime,endTime,index, limit);
		Integer num=salesService.selectCount(customerid, goodsid, orderid,startTime,endTime);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
	}
	
	/**
     * 添加商品销售信息
     * @param salesVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("addSales")
    public ResultObj addSales(@RequestBody SalesRequest request){
        try {
        	String operateperson = (String) httpSession.getAttribute("username");
        	Date salestime=new Date();
        	List<SalesItem> products=request.getProducts();
        	if(products!=null && !products.isEmpty()) {
        		for(SalesItem salesItem:products) {
        			Integer actualCount=goodsService.selectGoodsActualCount(salesItem.getGoodsid());
        			if(salesItem.getNumber()>actualCount) {
        				throw new Exception("Sales Fail:The sales volume is greater than the inventory quantity./销售失败:销售数量大于库存数量");
        			}
        		}
        	}
        	
        	if(products!=null && !products.isEmpty()) {
        		for(SalesItem salesItem:products) {
            		salesService.insert(request.getOrderid(),request.getCustomerid(), salesItem.getPaytype(), salestime, operateperson, salesItem.getNumber(), salesItem.getRemark(), salesItem.getSaleprice(), salesItem.getGoodsid());
        		}
        	}
        	
            return ResultObj.SALES_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SALES_ERROR;
        }
    }
    
    /**
     * 更新商品销售信息
     * @param salesVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("updateSales")
    public ResultObj updateSales(Integer id, String paytype, Integer number,
			String remark, BigDecimal saleprice){
        try {
        	String operateperson = (String) httpSession.getAttribute("username");
        	Date salestime=new Date();
            salesService.update(id, paytype, salestime, operateperson, number, remark, saleprice);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }
	
    /**
     * 删除商品销售信息
     * @param id
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("deleteSales")
    public ResultObj deleteSales(Integer id){
        try {
            salesService.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadAllOrders")
    public DataGridView loadAllOrders() {
    	List<String> list = salesService.selectOrders();
    	return new DataGridView(list!=null?list.size():0L,list);
    }
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadOrdersByCustomer")
    public DataGridView loadOrdersByCustomer(String customerid) {
    	List<String> list = salesService.selectByCustomer(customerid);
    	return new DataGridView(list!=null?list.size():0L,list);
    }
}
