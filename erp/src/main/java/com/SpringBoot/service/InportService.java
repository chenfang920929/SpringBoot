package com.SpringBoot.service;

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
			Double inportprice, Integer providerid, Integer goodsid) {
		inportImp.insert(inporttime, operateperson, number, remark, inportprice, providerid, goodsid);
	}

	@Override
	public void update(Integer id, Date inporttime, Integer number, String remark, Double inportprice,
			Integer providerid, Integer goodsid, String operateperson) {
		
		inportImp.update(id , inporttime, number, remark, inportprice, providerid, goodsid,operateperson);
		
	}

	@Override
	public void delete(Integer id) {
		inportImp.delete(id);
	}

	@Override
	public List<Inport> select(Integer providerid, Integer goodsid, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Inport> select = inportImp.select(providerid, goodsid, index, limit);
		return select;
	}
	
	@Override
	public Integer selectCount(Integer providerid, Integer goodsid) {
		Integer count = inportImp.selectCount(providerid, goodsid);
		return count;	
	}


	@Override
	public Inport selectById(Integer id) {
		// TODO 自动生成的方法存根
		return inportImp.selectById(id);
	}
}
