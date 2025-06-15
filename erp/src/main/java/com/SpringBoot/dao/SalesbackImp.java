package com.SpringBoot.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Salesback;

@Mapper
public interface SalesbackImp {

	public List<Salesback> select(
			@Param("customerid")String customerid,
			@Param("goodsid")Integer goodsid,
			@Param("orderno")String orderno,
			@Param("startTime") Date startTime,
		    @Param("endTime") Date endTime,
			@Param("index")Integer index,
			@Param("limit")Integer limit);
	
	public void delete(@Param("id")Integer id);
	
	public void insert(@Param("customerid")String customerid,@Param("paytype")String paytype,@Param("salesbacktime")Date salesbacktime,
			@Param("salebackprice")BigDecimal salebackprice,@Param("operateperson")String operateperson,
			@Param("number")Integer number,@Param("remark")String remark,@Param("goodsid")Integer goodsid,@Param("orderno")String orderno);

	public Integer selectCount(
			@Param("customerid")String customerid,
			@Param("goodsid")Integer goodsid,
			@Param("orderno")String orderno,
			@Param("startTime") Date startTime,
		    @Param("endTime") Date endTime);

	List<String> selectOrders();

	public List<String> selectByCustomer(@Param("customerid")String customerid);
}
