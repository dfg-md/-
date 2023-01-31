package com.zzs.guli.eduservice.client;

import com.zzs.guli.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-11 13:36
 **/

@FeignClient(name = "service-vod",fallback = vodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @ApiOperation(value = "根据视频id删除阿里云视频")
    @DeleteMapping("/vodService/video/removeAliyVideo/{id}")
    public R removeAliyVideo(@PathVariable("id") String id);


    @DeleteMapping("/vodService/video/deleteBatch")
    R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
