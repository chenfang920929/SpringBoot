package com.SpringBoot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.SpringBoot.bean.Customer;

@Mapper
public interface CustomerImp {

	public List<Customer> select(@Param("customername")String customername,@Param("connectionpersion")String connectionpersion,@Param("phone")String phone,
			@Param("index")Integer index,@Param("limit")Integer limit);
	
	public List<Customer> selectName();
	
	public void insert(@Param("customername")String customername,@Param("address")String address,@Param("connectionpersion")String connectionpersion
			,@Param("phone")String phone,@Param("email")String email);
	
	public void update(@Param("id")Integer id,@Param("customername")String customername,@Param("address")String address,@Param("connectionpersion")String connectionpersion
			,@Param("phone")String phone,@Param("email")String email);
	
	public void delete(Integer id);
}
