package com.SpringBoot.service;

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
	public List<Salesback> select(Integer customerid, Integer goodsid,Integer orderno,Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Salesback> select = salesbackImp.select(customerid, goodsid, orderno,index, limit);
		return select;
	}

	@Override
	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		salesbackImp.delete(id);
	}

	@Override
	public void insert(Integer customerid, String paytype, Date salesbacktime, Double salebackprice,
			String operateperson, Integer number, String remark, Integer goodsid, Integer orderno) {
		// TODO 自动生成的方法存根
		salesbackImp.insert(customerid, paytype, salesbacktime, salebackprice, operateperson, number, remark, goodsid,orderno);
	}

	@Override
	public Integer selectCount(Integer customerid, Integer goodsid,Integer orderno) {
		Integer count = salesbackImp.selectCount(customerid, goodsid,orderno);
		return count;	
	}

}
