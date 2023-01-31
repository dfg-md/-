package com.zzs.guli.eduservice.client;

import com.zzs.guli.util.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-12 17:42
 * 出错之后会执行
 **/


@Component
public class vodFileDegradeFeignClient  implements VodClient{
    @Override
    public R removeAliyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
