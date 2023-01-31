package com.zzs.guli.eduservice.service;

import com.zzs.guli.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
public interface EduVideoService extends IService<EduVideo> {


    List<EduVideo> getAllVideo(String videoId);

    boolean removeVideoById(String courseId);
}
