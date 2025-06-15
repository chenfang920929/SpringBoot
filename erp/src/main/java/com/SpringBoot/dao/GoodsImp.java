package com.SpringBoot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Goods;

@Mapper
public interface GoodsImp {

	public List<Goods> select(@Param("providerid")Integer providerid,@Param("goodsname")String goodsname,@Param("productcode")String productcode,@Param("size")String size,@Param("index")Integer index,@Param("limit")Integer limit);
	
	public Integer selectCount(@Param("providerid")Integer providerid,@Param("goodsname")String goodsname,@Param("productcode")String productcode,@Param("size")String size);
	
	public void insert(@Param("providerid")Integer providerid,@Param("goodsname")String goodsname,@Param("productcode")String productcode,@Param("description")String description,@Param("size")String size,@Param("number")Integer number);
	
	public void delete(@Param("id")Integer id);
	
	public void update(@Param("id")Integer id,@Param("providerid")Integer providerid,@Param("goodsname")String goodsname,@Param("productcode")String productcode,@Param("description")String description,@Param("size")String size,@Param("number")Integer number);
	
	public List<Goods> selectGoodsName();
	
	public void updateNumber(@Param("id")Integer id,@Param("number")Integer number);
	
	public List<Goods> selectByProviderid(@Param("providerid")Integer providerid);
	
	public Goods selectById(@Param("id")Integer id);

	public Goods selectByProductcode(@Param("productcode")String productcode,@Param("size")String size);
	
}
