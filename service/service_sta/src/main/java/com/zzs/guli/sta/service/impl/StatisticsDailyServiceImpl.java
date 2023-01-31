package com.zzs.guli.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.sta.client.UcenterClient;
import com.zzs.guli.sta.entity.StatisticsDaily;
import com.zzs.guli.sta.mapper.StatisticsDailyMapper;
import com.zzs.guli.sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.util.R;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-01-01
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


   @Autowired
   private UcenterClient ucenterClient;


    @Override
    public void registerCount(String day) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        this.baseMapper.delete(wrapper);

        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");
        if (countRegister == 0 ){
            throw new GuliException(20001,"今天注册人数为0");
        }
        //把获取到的数据添加到统计表
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));

        int insert = this.baseMapper.insert(statisticsDaily);
        if (insert <=0){
            throw new GuliException(20001,"你的网络不佳 请重试");
        }
    }

    @Override
    public Map<String, Object> getShowDate(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);

        List<StatisticsDaily> statisticsDailies = this.baseMapper.selectList(wrapper);
        System.out.println("ssssssssssssssssssssss"+statisticsDailies);
        System.out.println();
        System.out.println();
        List<String> day = new ArrayList<>();
        List<Integer> num = new ArrayList<>();

        for (int i = 0; i <statisticsDailies.size() ; i++) {
            StatisticsDaily statisticsDaily = statisticsDailies.get(i);
            day.add(statisticsDaily.getDateCalculated());
            switch (type) {
                case "register_num":
                    num.add(statisticsDaily.getRegisterNum());
                    break;
                case "login_num":
                    num.add(statisticsDaily.getLoginNum());
                    break;
                case "video_view_num":
                    num.add(statisticsDaily.getVideoViewNum());
                    break;
                case "course_num":
                    num.add(statisticsDaily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("date_calculatedList",day);
        map.put("numDataList",num);

        return map;
    }
}
