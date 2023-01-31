package com.zzs.guli.msm.controller;

import cn.hutool.core.util.RandomUtil;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.msm.service.MsmService;
import com.zzs.guli.util.R;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-15 11:55
 **/
@RestController
@RequestMapping("/MsmService/msm")
//@CrossOrigin
public class MsmController {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MsmService service;

//    发送验证码方法
    @GetMapping("{phone}")
    public R sendMsm(@PathVariable String phone){
        String code;
//        code = redisTemplate.opsForValue().get(phone).toString();
//        if (!StringUtils.isEmpty(code)){
//            System.out.println("异常了");
//            throw new GuliException(20001,"你的网络不佳 请重试");
//        }

        code = RandomUtil.randomNumbers(6);
        service.send(code,phone);

        redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        return R.ok();
    }

}
