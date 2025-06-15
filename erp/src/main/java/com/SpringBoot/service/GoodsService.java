package com.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Goods;
import com.SpringBoot.dao.GoodsImp;

@Service
public class GoodsService implements GoodsImp {

	@Autowired
	GoodsImp goodsImp;
	
	
	@Override
	public void insert(Integer providerid, String goodsname, String productcode, String description,String size, Integer number){
		goodsImp.insert(providerid, goodsname, productcode,description, size,  number);
	}

	@Override
	public void delete(Integer id) {
		goodsImp.delete(id);		
	}

	@Override
	public void update(Integer id, Integer providerid, String goodsname, String productcode, String description,String size, Integer number) {
		goodsImp.update(id, providerid, goodsname, productcode,description, size,  number);		
	}


	@Override
	public void updateNumber(Integer id, Integer number) {
		goodsImp.updateNumber(id, number);
		
	}

	@Override
	public List<Goods> select(Integer providerid, String goodsname, String productcode,String size, Integer index, Integer limit) {
		// TODO 自动生成的方法存根
		List<Goods> select = goodsImp.select(providerid, goodsname, productcode, size, index, limit);
		return select;
	}
	
	@Override
	public Integer selectCount(Integer providerid, String goodsname, String productcode,String size) {
		// TODO 自动生成的方法存根
		Integer count = goodsImp.selectCount(providerid, goodsname, productcode, size);
		return count;
	}

	@Override
	public List<Goods> selectGoodsName() {
		// TODO 自动生成的方法存根
		List<Goods> selectGoodsName = goodsImp.selectGoodsName();
		return selectGoodsName;
	}

	@Override
	public List<Goods> selectByProviderid(Integer providerid) {
		// TODO 自动生成的方法存根
		return goodsImp.selectByProviderid(providerid);
	}

	@Override
	public Goods selectById(Integer id) {
		// TODO 自动生成的方法存根
		return goodsImp.selectById(id);
	}

	@Override
	public Goods selectByProductcode(String productcode,String size) {
		// TODO 自动生成的方法存根
		return goodsImp.selectByProductcode(productcode,size);
	}
}
