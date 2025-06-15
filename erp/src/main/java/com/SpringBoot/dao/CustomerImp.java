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
	
	public List<Customer> selectAvailableName();
	
	public void insert(@Param("id")String id,@Param("customername")String customername,@Param("address")String address,@Param("connectionpersion")String connectionpersion
			,@Param("phone")String phone,@Param("email")String email,@Param("available")Integer available);
	
	public void update(@Param("id")String id,@Param("customername")String customername,@Param("address")String address,@Param("connectionpersion")String connectionpersion
			,@Param("phone")String phone,@Param("email")String email,@Param("available")Integer available);
	
	public void delete(@Param("id")String id);

	public Integer selectCount(@Param("customername")String customername,@Param("connectionpersion")String connectionpersion,@Param("phone")String phone);
	
	// 查询当前序列值（加锁）
	public Long getCurrentSeq();
 
    // 更新序列值 
	public void incrementSeq();
}
