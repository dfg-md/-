package com.zzs.guli.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.zzs.guli.eduservice.entity.EduTeacher;
import com.zzs.guli.eduservice.service.EduCourseService;
import com.zzs.guli.eduservice.service.EduTeacherService;
import com.zzs.guli.util.R;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-14 19:19
 **/
@RestController
//@CrossOrigin
@RequestMapping("/eduService/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService CourseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping()
    public R index(){
        // 前8门课程
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.orderByAsc("id");
        eduCourseQueryWrapper.last("limit 8");
        List<EduCourse> CourseList  = CourseService.list(eduCourseQueryWrapper);

        // 前4名讲师
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByAsc("id");
        eduTeacherQueryWrapper.last("limit 4");
        List<EduTeacher> TeacherList  = teacherService.list(eduTeacherQueryWrapper);
        return R.ok().data("eduList",CourseList).data("teacherList",TeacherList);
    }
}
