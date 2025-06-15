package com.SpringBoot.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Inport;

@Mapper
public interface InportImp {

	public List<Inport> select(
			@Param("providerid")Integer providerid,
			@Param("goodsid")Integer goodsid,			
			@Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
			@Param("index")Integer index,
			@Param("limit")Integer limit);
	
	public void insert(@Param("inporttime")Date inporttime,@Param("operateperson")String operateperson,@Param("number")Integer number,@Param("remark")String remark
			,@Param("inportprice")BigDecimal inportprice,@Param("providerid")Integer providerid,@Param("goodsid")Integer goodsid,@Param("carton")Double carton);
	
	public void update (@Param("id")Integer id,@Param("inporttime")Date inporttime,@Param("number")Integer number,@Param("remark")String remark
			,@Param("inportprice")BigDecimal inportprice,@Param("operateperson")String operateperson,@Param("carton")Double carton);
	
	public void delete(@Param("id")Integer id);
	
	public Inport selectById(@Param("id")Integer id);

	public Integer selectCount(
			@Param("providerid")Integer providerid,
			@Param("goodsid")Integer goodsid,
			@Param("startTime") Date startTime,
            @Param("endTime") Date endTime);
	
}
