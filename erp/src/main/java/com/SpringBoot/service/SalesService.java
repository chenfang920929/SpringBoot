package com.SpringBoot.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Sales;
import com.SpringBoot.dao.SalesImp;

@Service
public class SalesService implements SalesImp {
	
	@Autowired
	SalesImp salesImp;
	
	@Override
	public List<Sales> select(String customerid, Integer goodsid,String orderid, Date startTime, Date endTime,Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Sales> select = salesImp.select(customerid, goodsid,orderid, startTime,endTime,index, limit);
		return select;
	}

	@Override
	public void insert(String orderid,String customerid, String paytype, Date salestime, String operateperson, Integer number,
			String remark, BigDecimal saleprice, Integer goodsid) {
		// TODO 自动生成的方法存根
		salesImp.insert(orderid,customerid, paytype, salestime, operateperson, number, remark, saleprice, goodsid);
	}

	@Override
	public void update(Integer id, String paytype, Date salestime, String operateperson,
			Integer number, String remark, BigDecimal saleprice) {
		// TODO 自动生成的方法存根
		salesImp.update(id, paytype, salestime, operateperson, number, remark, saleprice);
	}

	@Override
	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		salesImp.delete(id);
	}

	@Override
	public Sales selectById(Integer id) {
		// TODO 自动生成的方法存根
		return salesImp.selectById(id);
	}

	@Override
	public void updateNumber(Integer id, Integer number) {
		// TODO 自动生成的方法存根
		salesImp.updateNumber(id, number);
	}

	@Override
	public Integer selectCount(String customerid, Integer goodsid, String orderid,Date startTime, 
            Date endTime) {
		Integer count = salesImp.selectCount(customerid, goodsid, orderid,startTime,endTime);
		return count;	
	}
	
	@Override
	public List<String> selectOrders() {
		// TODO 自动生成的方法存根
		List<String> selectOrders = salesImp.selectOrders();
		return selectOrders;
	}

	@Override
	public List<String> selectByCustomer(String customerid) {
		// TODO 自动生成的方法存根
		return salesImp.selectByCustomer(customerid);
	}
}

