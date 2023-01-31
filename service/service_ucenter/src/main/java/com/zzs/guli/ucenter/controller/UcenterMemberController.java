package com.zzs.guli.ucenter.controller;


import com.zzs.guli.ucenter.entity.UcenterMember;
import com.zzs.guli.ucenter.entity.vo.registerVo;
import com.zzs.guli.ucenter.entity.vo.loginVo;
import com.zzs.guli.ucenter.service.UcenterMemberService;
import com.zzs.guli.util.JwtUtils;
import com.zzs.guli.util.R;
import com.zzs.guli.util.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-12-15
 */
@RestController
@RequestMapping("/ucnterService/member")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService service;

    //登录
    @ApiOperation("用户登录方法测试")
    @PostMapping()
    public R login(@RequestBody loginVo user){
        String s = service.loginUser(user);
        System.out.println("s  ===="  + s);
        if (s == null){

        }
        return  R.ok().data("token",service.loginUser(user));
    }

    //注册方法
    @ApiOperation("用户注册方法测试")
    @PostMapping("zhu")
    public R Resister(@RequestBody registerVo user){
        System.out.println(user);
        service.register(user);
        return R.ok();
    }

    @ApiOperation("验证用户是否登录")
    @GetMapping()
    public R getUserByid(HttpServletRequest request){
        //根据JWT 工具类 获取 对象信息头信息
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        return R.ok().data("userInfo",service.getById(memberIdByJwtToken));
    }

    @GetMapping("getUser/{id}")
    public UcenterMemberOrder getUserById(@PathVariable String id){
        UcenterMember member = service.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

//    查询某一天的注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day){
       Integer integer = service.countRegister(day);
        return R.ok().data("countRegister",integer);
    }
}

