package com.SpringBoot.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Salesback;
import com.SpringBoot.dao.SalesbackImp;

@Service
public class SalesbackService implements SalesbackImp{
	
	@Autowired
	SalesbackImp salesbackImp;

	@Override
	public List<Salesback> select(String customerid, Integer goodsid,String orderno, Date startTime, Date endTime,Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Salesback> select = salesbackImp.select(customerid, goodsid, orderno, startTime,endTime,index, limit);
		return select;
	}

	@Override
	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		salesbackImp.delete(id);
	}

	@Override
	public void insert(String customerid, String paytype, Date salesbacktime, BigDecimal salebackprice,
			String operateperson, Integer number, String remark, Integer goodsid, String orderno) {
		// TODO 自动生成的方法存根
		salesbackImp.insert(customerid, paytype, salesbacktime, salebackprice, operateperson, number, remark, goodsid,orderno);
	}

	@Override
	public Integer selectCount(String customerid, Integer goodsid,String orderno, Date startTime, Date endTime) {
		Integer count = salesbackImp.selectCount(customerid, goodsid,orderno,startTime,endTime);
		return count;	
	}

	@Override
	public List<String> selectOrders() {
		// TODO 自动生成的方法存根
		List<String> selectOrders = salesbackImp.selectOrders();
		return selectOrders;
	}

	@Override
	public List<String> selectByCustomer(String customerid) {
		// TODO 自动生成的方法存根
		return salesbackImp.selectByCustomer(customerid);
	}
}
