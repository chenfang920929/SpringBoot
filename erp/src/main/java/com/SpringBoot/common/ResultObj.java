package com.SpringBoot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 落亦-
 * @Date: 2019/11/21 21:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    private Integer code;
    private String msg;
    
    public static final ResultObj LOGIN_SUCCESS=new ResultObj(Constast.OK,"Landing successful/登陆成功");
    public static final ResultObj LOGIN_ERROR_PASS=new ResultObj(Constast.ERROR,"Username or password error/用户名或密码错误");
    public static final ResultObj LOGIN_ERROR_CODE=new ResultObj(Constast.ERROR,"Captcha error/验证码错误");

    public static final ResultObj ADD_SUCCESS = new ResultObj(Constast.OK,"Add Success/添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Constast.ERROR,"Add Fail/添加失败");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(Constast.OK,"Delete Success/删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Constast.ERROR,"Delete Fail/删除失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Constast.OK,"Upate Success/修改成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Constast.ERROR,"Upate Fail/修改失败");

    public static final ResultObj RESET_SUCCESS = new ResultObj(Constast.OK,"Reset Success/重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Constast.ERROR,"Reset Fail/重置失败");

    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Constast.OK,"分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Constast.ERROR,"分配失败");
    
    public static final ResultObj SALES_SUCCESS = new ResultObj(Constast.OK,"Sales Success/销售成功");
    public static final ResultObj SALES_ERROR = new ResultObj(Constast.ERROR,"Sales Fail:The sales volume is greater than the inventory quantity./销售失败:销售数量大于库存数量");

    public static final ResultObj BACKINPORT_SUCCESS = new ResultObj(Constast.OK,"SalesBack Success/退货成功");
    public static final ResultObj BACKINPORT_ERROR = new ResultObj(Constast.ERROR,"SalesBack Fail/退货失败");
    
    public static final ResultObj RUN_SUCCESS = new ResultObj(Constast.OK,"Inport Success/入库成功");
    public static final ResultObj RUN_ERROR = new ResultObj(Constast.ERROR,"Inport Fail/入库失败");
    
    public static final ResultObj SYNCCACHE_SUCCESS = new ResultObj(Constast.OK,"同步缓存成功");

    public static final ResultObj DELETE_ERROR_NEWS = new ResultObj(Constast.ERROR,"删除用户失败，该用户是其他用户的直属领导，请先修改该用户的下属的直属领导，再进行删除操作");
    public static final ResultObj DELETE_QUERY = new ResultObj();
    

}
