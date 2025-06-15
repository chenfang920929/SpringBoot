package com.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Provider;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.ProviderService;

@RestController
@RequestMapping("provider")
public class ProviderController {
	
	@Autowired
	ProviderService providerService;
	
	@Autowired
	LayuiJson layuiJson;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllProvider")
	public LayuiJson<Provider> loadAllProvider(String providername,String connectionperson,String phone,Integer page,Integer limit) {
		int index=(page-1)*limit;
		List<Provider> data = providerService.select(providername, connectionperson, phone, index, limit);
		Integer num=providerService.selectCount(providername, connectionperson, phone);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
	}
	
	 @LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	 @RequestMapping("loadAllProviderForSelect")
	 public DataGridView loadAllProviderForSelect() {
	        List<Provider> list = this.providerService.selectAvailable();
	        return new DataGridView(list!=null?list.size():0L,list);
		}
	 
	 /**
	     * 添加一个供应商
	     * @param providerVo
	     * @return
	     */
		@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	    @RequestMapping("addProvider")
	    public ResultObj addProvider(String providername, String address, String connectionperson,String phone){
	        try {
	            providerService.insert(providername, address, connectionperson, phone);
	            return ResultObj.ADD_SUCCESS;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResultObj.ADD_ERROR;
	        }
	    }
	    
	    
	    /**
	     * 修改一个供应商
	     * @param providerVo
	     * @return
	     */
		@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	    @RequestMapping("updateProvider")
	    public ResultObj updateProvider(Integer id, String providername, String address, String connectionperson, String phone){
	        try {
	            providerService.update(id, providername, address, connectionperson, phone);
	            return ResultObj.UPDATE_SUCCESS;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResultObj.UPDATE_ERROR;
	        }
	    }
	    
	    
	    /**
	     * 删除一个供应商
	     * @param id
	     * @return
	     */
		@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	    @RequestMapping("deleteProvider")
	    public ResultObj deleteProvider(Integer id){
	        try {
	            providerService.delete(id);
	            return ResultObj.DELETE_SUCCESS;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResultObj.DELETE_ERROR;
	        }
	    }
	    
}