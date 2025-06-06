package com.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.bean.Provider;
import com.SpringBoot.dao.ProviderImp;

@Service
public class ProviderService implements ProviderImp {

	@Autowired
	ProviderImp providerImp;
	
	@Override
	public void insert(String providername, String address, String connectionperson,String phone) {
		providerImp.insert(providername, address, connectionperson, phone);
	}

	@Override
	public void update(Integer id, String providername, String address,
			String connectionperson, String phone) {
		providerImp.update(id, providername, address,connectionperson, phone);		
	}

	@Override
	public void delete(Integer id) {
		providerImp.delete(id);		
	}

	@Override
	public List<Provider> select(String providername, String connectionperson, String phone, Integer index,
			Integer limit) {
		List<Provider> select = providerImp.select(providername, connectionperson, phone, index, limit);
		return select;
	}

	@Override
	public Integer selectCount(String providername, String connectionperson, String phone) {
		Integer count = providerImp.selectCount(providername, connectionperson,phone);
		return count;	
	}
	
	@Override
	public Integer selectProviderId(String providername) {
		Integer providerId = providerImp.selectProviderId(providername);
		return providerId;	
	}
	
	@Override
	public List<Provider> selectAvailable() {
		// TODO Auto-generated method stub
		return providerImp.selectAvailable();		
	}
}
