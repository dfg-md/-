package com.zzs.guli.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzs.guli.eduservice.entity.frontVo.CourseFrontVo;
import com.zzs.guli.eduservice.entity.frontVo.CourseWebVo;
import com.zzs.guli.eduservice.entity.vo.CourseInfoVo;
import com.zzs.guli.eduservice.entity.vo.CoursePublishVo;
import com.zzs.guli.eduservice.entity.vo.CourseQuery;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseByid(String courseId);


    void upDateCourse(CourseInfoVo courseInfoVo);

    CoursePublishVo publicCourseInfo(String courseId);

    IPage pageCourseCondition(Long current, Long limit, CourseQuery courseQuery);

    Boolean removeCourse(String courseId);

    Map<String, Object> getPage(Page<EduCourse> pageCourse, CourseFrontVo vo);

    CourseWebVo getBaseCourseInfo(String id);
}
