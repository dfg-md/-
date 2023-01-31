package com.zzs.guli.eduservice.service;

import com.zzs.guli.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    boolean removeDescriptionById(String courseId);
}
