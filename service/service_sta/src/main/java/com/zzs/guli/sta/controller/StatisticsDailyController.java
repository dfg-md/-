package com.zzs.guli.sta.controller;


import com.zzs.guli.sta.client.UcenterClient;
import com.zzs.guli.sta.service.StatisticsDailyService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-01-01
 */
@RestController
@RequestMapping("/staService/sta")
public class StatisticsDailyController {


    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //查询某一天注册人数 并加入到统计表
    @PostMapping("{day}")
    public R registerCount(@PathVariable String day){
        statisticsDailyService.registerCount(day);
        return R.ok();
    }

    @GetMapping("show/{type}/{begin}/{end}")
    public R getDate(@PathVariable String type,@PathVariable String begin,
                     @PathVariable String end){
      Map<String, Object> map = statisticsDailyService.getShowDate(type,begin,end);
      return R.ok().data(map);
    }

}

