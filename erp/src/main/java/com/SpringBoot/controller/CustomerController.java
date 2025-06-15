package com.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Customer;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	LayuiJson layuiJson;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllCustomer")
	public LayuiJson<Customer> loadAllCustomer(String customername,String connectionpersion,
			String phone,Integer page,Integer limit){
		int index=(page-1)*limit;
		List<Customer> data = customerService.select(customername, connectionpersion, phone, index, limit);
		Integer num=customerService.selectCount(customername, connectionpersion, phone);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
	}
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllCustomerForSelect")
	public DataGridView loadAllCustomerForSelect(){
		
		List<Customer> list = customerService.selectName();
		return new DataGridView(list!=null?list.size():0L,list);
	}
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAvailableCustomerForSelect")
	public DataGridView loadAvailableCustomerForSelect(){
		
		List<Customer> list = customerService.selectAvailableName();
		return new DataGridView(list!=null?list.size():0L,list);
	}
	
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("updateCustomer")
    public ResultObj updateCustomer(String id, String customername, String address, 
			String connectionpersion, String phone, String email,Integer available){
        try {
        	
            customerService.update(id, customername, address, 
            		connectionpersion, phone, email,available);
            
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("addCustomer")
    public ResultObj addCustomer(String customername, String address, String connectionpersion,
			String phone, String email,Integer available){
        try {
    		String customerId = generateCustomerId();
            customerService.insert(customerId,customername, address, 
            		connectionpersion, phone, email,available);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(String id){
        try {
            customerService.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
	
    /**
     * 生成 ZHT_0000001 格式的ID 
     */
    @Transactional 
    public String generateCustomerId() {
        // 1. 查询当前序列值（加锁）
        Long nextVal = customerService.getCurrentSeq(); 
 
        // 2. 格式化ID（ZHT_0000001）
        String newId = String.format("ZHT-%06d",  nextVal);
 
        // 3. 更新序列值 
        customerService.incrementSeq(); 
 
        return newId;
    }

}
