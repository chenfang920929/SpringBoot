package com.SpringBoot.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Goods;
import com.SpringBoot.bean.Inport;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.GoodsService;
import com.SpringBoot.service.InportService;

@RestController
@RequestMapping("inport")
public class InportController {
	
	@Autowired
	LayuiJson layuiJson;
	
	@Autowired
	InportService inportService;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	GoodsService goodsService;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
	@RequestMapping("loadAllInport")
	public LayuiJson<Inport> loadAllInport(Integer providerid,Integer goodsid,Integer page,Integer limit
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime
			,@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
		int index=(page-1)*limit;
		List<Inport> data = inportService.select(providerid, goodsid,startTime,endTime,index, limit);
		Integer num=inportService.selectCount(providerid, goodsid,startTime,endTime);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
		
	}
	
	  /**
     * 添加进货商品
     * @param inportVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("addInport")
    public ResultObj addInport(Integer providerid,Integer goodsid,Integer number,BigDecimal inportprice,String remark,Double carton){
        try {
        	Date inporttime = new Date();
        	String operateperson = (String) httpSession.getAttribute("username");                                     
        	inportService.insert(inporttime, operateperson, number, remark, inportprice, providerid, goodsid,carton);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    
    
    /**
     * 更新进货商品
     * @param inportVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("updateInport")
    public ResultObj updateInport(Integer id,Integer number,BigDecimal inportprice,String remark,Double carton){
        try {
        	Date inporttime = new Date();
        	String operateperson = (String) httpSession.getAttribute("username");
            inportService.update(id, inporttime, number, remark, inportprice, operateperson,carton);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }
    
    /**
     * 删除进货商品
     * @param id
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            inportService.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("run")
    public ResultObj runInport(Integer id,Integer number) {
    	
    	try {
    		
    		Inport i = inportService.selectById(id);
    		Date inporttime = new Date();
    		String remark = i.getRemark();
        	Integer goodsid = i.getGoodsid();
        	BigDecimal inportprice = i.getInportprice();
        	String operateperson = (String) httpSession.getAttribute("username");
        	Double carton = i.getCarton();
        	
        	Goods g = goodsService.selectById(goodsid);
        	Integer goodsNumber = g.getNumber();
        	
    		if(i.getNumber()==number) {
    			
    			goodsService.updateNumber(goodsid, number+goodsNumber);
    			inportService.delete(id);
        		return ResultObj.RUN_SUCCESS;
        		
        	}else if(i.getNumber()>number){
        		goodsService.updateNumber(goodsid, number+goodsNumber);
        		inportService.update(id, inporttime, i.getNumber()-number,remark, 
        				inportprice,  operateperson,carton);
        		
        		return ResultObj.RUN_SUCCESS;
        	}else {
        		
        		
        		return ResultObj.RUN_ERROR;
        	}
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResultObj.RUN_ERROR;
		}
    	
    } 
    
    
    

}
