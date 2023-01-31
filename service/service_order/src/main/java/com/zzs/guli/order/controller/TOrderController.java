package com.zzs.guli.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.order.entity.TOrder;
import com.zzs.guli.order.service.TOrderService;
import com.zzs.guli.util.JwtUtils;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-12-26
 */
@RestController
@RequestMapping("/orderService/order")
//@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService service;

    @PostMapping("/{courserId}")
    public R addOrder(@PathVariable("courserId") String courserId ,
                      HttpServletRequest request){
        String orderId = service.createOrder(courserId,JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId",orderId);
    }

    @GetMapping("/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
      QueryWrapper<TOrder> wrapper  = new QueryWrapper<>();
      wrapper.eq("order_no",orderId);
      TOrder one = service.getOne(wrapper);
      return R.ok().data("item",one);
    }

}

