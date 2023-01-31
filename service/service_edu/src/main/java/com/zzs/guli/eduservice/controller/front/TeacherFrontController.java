package com.zzs.guli.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.zzs.guli.eduservice.entity.EduTeacher;
import com.zzs.guli.eduservice.entity.vo.CourseInfoVo;
import com.zzs.guli.eduservice.service.EduCourseService;
import com.zzs.guli.eduservice.service.EduTeacherService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-22 15:21
 **/
@RequestMapping("/eduService/teacherFront")
@RestController
//@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService service;


    @Autowired
    private EduCourseService CourseService;


    @PostMapping("{page}/{limit}")
    public R getPage(@PathVariable long page ,
                     @PathVariable long limit
                     ){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);

      Map<String,Object> map = service.getTeacherFrontList(teacherPage);

      return R.ok().data(map);

    }

    @GetMapping("{id}")
    public R  getById(@PathVariable String id){
        EduTeacher byId = service.getById(id);

        QueryWrapper<EduCourse> wrapper  = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> list = CourseService.list(wrapper);
        if (byId == null){
           throw new GuliException(20001,"查询晒白");
        }

      return   R.ok().data("teacher",byId).data("courseList",list);

    }



}
