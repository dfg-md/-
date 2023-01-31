package com.zzs.guli.eduservice.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.zzs.guli.eduservice.entity.EduChapter;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.zzs.guli.eduservice.entity.chapter.ChapterVo;
import com.zzs.guli.eduservice.entity.frontVo.CourseFrontVo;
import com.zzs.guli.eduservice.entity.frontVo.CourseWebVo;
import com.zzs.guli.eduservice.entity.vo.CourseInfoVo;
import com.zzs.guli.eduservice.service.EduChapterService;
import com.zzs.guli.eduservice.service.EduCourseService;
import com.zzs.guli.util.R;
import com.zzs.guli.util.ordervo.CourseWebVoOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-22 17:54
 **/

@RequestMapping("/eduService/courseFront")
@RestController
//@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService service;

    @Autowired
    private EduChapterService ChapterService;


    @PostMapping("{page}/{limit}")
    public R getPage(@PathVariable long page ,
                          @PathVariable long limit,
                          @RequestBody(required = false)  CourseFrontVo vo){
        System.out.println(vo);
        Page<EduCourse> pageCourse = new Page(page,limit);

       Map<String ,Object> map =service.getPage(pageCourse,vo);

        return R.ok().data(map);
    }

    @GetMapping("{id}")
    public R getPage(@PathVariable String id) {

       CourseWebVo vo = service.getBaseCourseInfo(id);

       List<ChapterVo> chapterVideoByCourseId = ChapterService.getChapterVideoByCourseId(id);


       return R.ok().data("courseWebVo",vo).data("chapterVideoList",chapterVideoByCourseId);
    }

    @PostMapping("{id}")
    public CourseWebVoOrder getCourseById(@PathVariable String id){
        CourseWebVo baseCourseInfo = service.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
