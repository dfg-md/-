package com.zzs.guli.order.service;

import com.zzs.guli.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-12-26
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courserId, String memberIdByJwtToken);
}
