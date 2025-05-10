package com.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Customer;
import com.SpringBoot.dao.CustomerImp;


@Service
public class CustomerService implements CustomerImp{

	@Autowired
	CustomerImp customerImp;

	@Override
	public List<Customer> select(String customername,String connectionpersion,String phone,Integer index, Integer limit) {
		List<Customer> select = customerImp.select(customername, connectionpersion, phone, index, limit);
		return select;
	}

	@Override
	public List<Customer> selectName() {
		// TODO 自动生成的方法存根
		List<Customer> selectName = customerImp.selectName();
		return selectName;
	}

	@Override
	public void insert(String customername, String address, String connectionpersion,
			String phone, String email) {
		// TODO 自动生成的方法存根
		customerImp.insert(customername, address, connectionpersion, phone, email);
	}

	@Override
	public void update(Integer id, String customername, String address, String connectionpersion, String phone,String email) {
		// TODO 自动生成的方法存根
		customerImp.update(id, customername, address, connectionpersion, phone, email);
		
	}

	@Override
	public void delete(Integer id) {
		// TODO 自动生成的方法存根
		customerImp.delete(id);
	}
	
}
