package com.zzs.guli.sta.schedule;

import com.zzs.guli.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-01 15:42
 **/
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService service;

    //在每天1点 执行方法 把数据进行添加
    @Scheduled(cron = "0 0 1 * * ? ")
    public void cron(){
        long l = System.currentTimeMillis();
        l = l - (24 * 60 * 60 * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(l);
        System.out.println(format);
        service.registerCount(format);
    }

}
