package com.zzs.guli.order.controller;


import com.zzs.guli.order.service.TPayLogService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-12-26
 */
@RestController
@RequestMapping("orderService/paylog")
public class TPayLogController {

    @Autowired
    private TPayLogService service;

    @GetMapping("{orderNo}")
    public R createNative(@PathVariable String orderNo){
       Map map  = service.createNatvie(orderNo);
       System.out.println("返回二维码map集合====》》》》"+map);
       return R.ok().data(map);
    }

//    查询订单状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){

        Map<String ,String > map  = service.queryPayStatus(orderNo);
        System.out.println("返回查询结果的map集合====》》》》"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            service.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

