package com.SpringBoot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Provider;

@Mapper
public interface ProviderImp {

	public List<Provider> select(@Param("providername")String providername,@Param("connectionperson")String connectionperson,@Param("phone")String phone,@Param("index")Integer index,@Param("limit")Integer limit);
	
	public void insert(@Param("providername")String providername,@Param("address")String address,@Param("connectionperson")String connectionperson,@Param("phone")String phone);
	
	public void update(@Param("id")Integer id,@Param("providername")String providername,@Param("address")String address,@Param("connectionperson")String connectionperson,@Param("phone")String phone);
	
	public void delete(Integer id);
	
	public List<Provider> selectAvailable();
}
