package com.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.annotation.LogMethod;
import com.SpringBoot.bean.Goods;
import com.SpringBoot.common.DataGridView;
import com.SpringBoot.common.LayuiJson;
import com.SpringBoot.common.ResultObj;
import com.SpringBoot.service.GoodsService;

@RestController
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	LayuiJson layuiJson;
	
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadAllGoods")
	public LayuiJson<Goods> loadAllGoods(Integer providerid,String goodsname ,String productcode,String size,Integer page,Integer limit){
		
		int index=(page-1)*limit;
		List<Goods> data = goodsService.select(providerid, goodsname, productcode,  size, index, limit);
		Integer num=goodsService.selectCount(providerid, goodsname, productcode, size);
		layuiJson.setCode(0);
		layuiJson.setCount(num);
		layuiJson.setData(data);
		return layuiJson;
	}
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect() {
    	List<Goods> list = goodsService.selectGoodsName();
    	return new DataGridView(list!=null?list.size():0L,list);
    }
    
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid) {
    	List<Goods> list = goodsService.selectByProviderid(providerid);
    	return new DataGridView(list!=null?list.size():0L,list);
    }
    
    
    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("addGoods")
    public ResultObj addGoods(Integer providerid, String goodsname, String productcode, String description, String size,  Integer number){
        try {
        	goodsService.insert(providerid, goodsname, productcode, description, size ,number);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    
    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Integer id, Integer providerid, String goodsname, String productcode, String description,String size,Integer number){
        try {
            goodsService.update(id, providerid, goodsname, productcode, description,size,number);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    
    /**
     * 删除商品
     * @param id 商品id
     * @return
     */
	@LogMethod(trackTime = true, level = LogMethod.Level.DEBUG)
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id){
        try {
            goodsService.delete(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
