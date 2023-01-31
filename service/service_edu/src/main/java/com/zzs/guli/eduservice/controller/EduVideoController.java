package com.zzs.guli.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.client.VodClient;
import com.zzs.guli.eduservice.entity.EduVideo;
import com.zzs.guli.eduservice.service.EduVideoService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/eduService/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService service;

    @Autowired
    private VodClient client;


    //添加操作
    @PostMapping
    public R addVideoInfo(@RequestBody EduVideo eduVideo){
        System.out.println(eduVideo);
        boolean save = service.save(eduVideo);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //修改
    @PutMapping
    public R updateVideoInfo(@RequestBody EduVideo eduVideo){
        boolean b = service.updateById(eduVideo);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //删除 TODO 同时需要删除小节中的视频
    @DeleteMapping("/{videoId}")
    public R deleteVideoInfo(@PathVariable String videoId){
        EduVideo byId = service.getById(videoId);
        String videoSourceId = byId.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId) ) {
            client.removeAliyVideo(videoSourceId);
        }
        //根据视频id 删除
        boolean b = service.removeById(videoId);

        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //根据id 查询
    @GetMapping("{videoId}")
    public R getVideoInfoById(@PathVariable String videoId){
        EduVideo byId = service.getById(videoId);
        if (byId==null){
            throw new GuliException(20001,"获取信息为空");
        }else {
            return R.ok().data("list",byId);
        }
    }

    // 查询所有
    @GetMapping("getAll/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
       List<EduVideo> list = service.getAllVideo(videoId);
       return R.ok().data("list",list);
    }



}

