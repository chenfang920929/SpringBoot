package com.SpringBoot.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Inport;
import com.SpringBoot.dao.InportImp;

@Service
public class InportService implements InportImp {

	@Autowired
	InportImp inportImp;
	
	
	public InportImp getInportImp() {
		return inportImp;
	}

	public void setInportImp(InportImp inportImp) {
		this.inportImp = inportImp;
	}


	@Override
	public void insert(Date inporttime, String operateperson, Integer number, String remark,
			BigDecimal inportprice, Integer providerid, Integer goodsid,Double carton) {
		inportImp.insert(inporttime, operateperson, number, remark, inportprice, providerid, goodsid, carton);
	}

	@Override
	public void update(Integer id, Date inporttime, Integer number, String remark, BigDecimal inportprice, String operateperson,Double carton) {
		
		inportImp.update(id , inporttime, number, remark, inportprice,operateperson,carton);
		
	}

	@Override
	public void delete(Integer id) {
		inportImp.delete(id);
	}

	@Override
	public List<Inport> select(Integer providerid, Integer goodsid, Date startTime, Date endTime, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Inport> select = inportImp.select(providerid, goodsid, startTime,endTime,index, limit);
		return select;
	}
	
	@Override
	public Integer selectCount(Integer providerid, Integer goodsid, Date startTime, Date endTime) {
		Integer count = inportImp.selectCount(providerid, goodsid, startTime,endTime);
		return count;	
	}


	@Override
	public Inport selectById(Integer id) {
		// TODO 自动生成的方法存根
		return inportImp.selectById(id);
	}
}
