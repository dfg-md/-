package com.zzs.guli.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.util.R;
import com.zzs.guli.vod.service.vodService;
import com.zzs.guli.vod.util.ConstantVodUtils;
import com.zzs.guli.vod.util.InitVodClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.zzs.guli.vod.util.ConstantVodUtils.ACCESS_KEY_ID;
import static com.zzs.guli.vod.util.ConstantVodUtils.ACCESS_KEY_SECRET;


/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-07 15:30
 **/

@RestController
@RequestMapping("/vodService/video")
//@CrossOrigin
public class vodController {

    @Autowired
    private vodService service;

    //上传视频到阿里云
    @PostMapping("uploadVideo")
    public R uploadAlyiVideo(MultipartFile file){
        String videoId = service.uploadVideoAly(file);
        System.out.println(videoId);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id 删除阿里云视频
    @ApiOperation(value = "根据视频id删除阿里云视频")
    @DeleteMapping("removeAliyVideo/{id}")
    public R removeAliyVideo(@PathVariable String id) {
        System.out.println("调用了service_vod 的删除视频方法");
        try{
            System.out.println(ACCESS_KEY_ID);
            System.out.println(ACCESS_KEY_SECRET);
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //想request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }

    @ApiOperation("删除多个阿里视频")
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        service.removeMoreVideo(videoIdList);

        return R.ok();
    }

    @GetMapping("{id}")
    public R getVideoID(@PathVariable String id) throws ClientException {
        System.out.println(id);
        try {
            //获取阿里云存储相关常量
            String accessKeyId = ACCESS_KEY_ID;
            String accessKeySecret = ACCESS_KEY_SECRET;
            //初始化
            DefaultAcsClient client = InitVodClient.initVodClient(accessKeyId, accessKeySecret);
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            //应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            return R.ok().message("获取凭证成功").data("playAuth",playAuth);
        } catch (com.aliyun.oss.ClientException e) {
            throw new GuliException(20001,"获取视频凭证失败");
        }
    }
}

