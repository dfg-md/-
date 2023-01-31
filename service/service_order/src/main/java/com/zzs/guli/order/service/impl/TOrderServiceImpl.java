package com.zzs.guli.order.service.impl;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import cn.hutool.db.sql.Order;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.order.client.EduClient;
import com.zzs.guli.order.client.UcenterClient;
import com.zzs.guli.order.entity.TOrder;
import com.zzs.guli.order.mapper.TOrderMapper;
import com.zzs.guli.order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.order.utils.number;
import com.zzs.guli.util.ordervo.CourseWebVoOrder;
import com.zzs.guli.util.ordervo.UcenterMemberOrder;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-12-26
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //生成订单的方法
    @Override
    public String createOrder(String courserId, String memberIdByJwtToken) {
        // 通过远程调用获取用户信息
        UcenterMemberOrder userById = ucenterClient.getUserById(memberIdByJwtToken);
        // 通过远程调用获取课程信息
        CourseWebVoOrder courseById = eduClient.getCourseById(courserId);

//        创建oder 对象 向order 添加数据
        TOrder order = new TOrder();
        order.setOrderNo(number.getOrderNo());// 订单号
        order.setCourseId(courserId); //课程id
        order.setCourseTitle(courseById.getTitle());
        order.setCourseCover(courseById.getCover());
        order.setTeacherName(courseById.getTeacherName());
        order.setTotalFee(courseById.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userById.getMobile());
        order.setNickname(userById.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        int insert = baseMapper.insert(order);
        if (insert <=0){
            throw new GuliException(20001,"添加订单失败");
        }
        return order.getOrderNo();
    }
}
