package com.SpringBoot.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Sales;

@Mapper
public interface SalesImp {

	public List<Sales> select(
			@Param("customerid")String customerid,
			@Param("goodsid")Integer goodsid,
			@Param("orderid")String orderid,
			@Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("index")Integer index,
            @Param("limit")Integer limit);
	
	public void insert(@Param("orderid")String orderid,@Param("customerid")String customerid,@Param("paytype")String paytype,@Param("salestime")Date salestime,@Param("operateperson")String operateperson,
			@Param("number")Integer number,@Param("remark")String remark,@Param("saleprice")BigDecimal saleprice,@Param("goodsid")Integer goodsid);
	
	public void update(@Param("id")Integer id,@Param("paytype")String paytype,@Param("salestime")Date salestime,@Param("operateperson")String operateperson,
			@Param("number")Integer number,@Param("remark")String remark,@Param("saleprice")BigDecimal saleprice);
	
	public void delete(@Param("id")Integer id);
	
	public Sales selectById(@Param("id")Integer id);
	
	public void updateNumber(@Param("id")Integer id,@Param("number")Integer number);

	public Integer selectCount(
			@Param("customerid")String customerid,
			@Param("goodsid")Integer goodsid,
			@Param("orderid")String orderid,
			@Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

	public List<String> selectOrders();
	
	public List<String> selectByCustomer(@Param("customerid")String customerid);

}
