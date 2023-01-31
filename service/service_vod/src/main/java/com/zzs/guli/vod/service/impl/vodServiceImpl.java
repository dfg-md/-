package com.zzs.guli.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.util.R;
import com.zzs.guli.vod.service.vodService;
import com.zzs.guli.vod.util.ConstantVodUtils;
import com.zzs.guli.vod.util.InitVodClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zzs.guli.vod.util.ConstantVodUtils.ACCESS_KEY_ID;
import static com.zzs.guli.vod.util.ConstantVodUtils.ACCESS_KEY_SECRET;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-07 15:31
 **/
@Service
public class vodServiceImpl implements vodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            System.out.println(title);
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request =
                    new UploadStreamRequest(ACCESS_KEY_ID, ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void removeMoreVideo(List<String> videoIdList) {

        System.out.println("调用了service_vod 的删除多个视频方法");
        try{
            System.out.println(ConstantVodUtils.ACCESS_KEY_ID);
            System.out.println(ConstantVodUtils.ACCESS_KEY_SECRET);
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            String collect = (String) videoIdList.stream().collect(Collectors.joining(","));
            //想request设置视频id
            request.setVideoIds(collect);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
//        String collect = list.stream().collect(Collectors.joining(","));
//        System.out.println(collect.toString());


//        filter  过滤
        List<String> collect = list.stream().filter(str -> str.contains("1"))
                .collect(Collectors.toList());
        System.out.println(collect);



    }
}
