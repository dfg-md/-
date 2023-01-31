package com.zzs.guli.eduservice.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.zzs.guli.eduservice.entity.vo.CourseInfoVo;
import com.zzs.guli.eduservice.entity.vo.CoursePublishVo;
import com.zzs.guli.eduservice.entity.vo.CourseQuery;
import com.zzs.guli.eduservice.entity.vo.TeacherQuery;
import com.zzs.guli.eduservice.service.EduCourseService;
import com.zzs.guli.util.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 * 图书简介
 * @author testjava
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/eduService/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService service;



    @ApiOperation(value = "条件查询带分页课程列表")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R getAllCourseList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) CourseQuery courseQuery){
            System.out.println(courseQuery);
            IPage<EduCourse> pageCourseCondition = service.pageCourseCondition(current,limit,courseQuery);
            long total = pageCourseCondition.getTotal();
            List<EduCourse> records = pageCourseCondition.getRecords();
            return R.ok().data("total",total).data("records",records);

    }

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = service.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    /**
     * 根据课程id 查询 课程
     * @param courseId
     * @return
     */
    @GetMapping("getCourseById/{courseId}")
    public R getCourseById(@PathVariable String courseId){
        CourseInfoVo Course  = service.getCourseByid(courseId);
        return R.ok().data("list",Course);
    }
    /**
     * 修改课程
     * @param courseInfoVo
     * @return
     */

    @PostMapping("updateCourse")
    public R UpdateCourseInfo(@RequestBody CourseInfoVo courseInfoVo ){
        System.out.println(courseInfoVo);
        service.upDateCourse(courseInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据id修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
//        service.upDateCourse(courseInfoVo);
        return R.ok();
    }

//根据id确认课程信息
    @ApiOperation(value = "根据id确认课程信息")
    @GetMapping("getPublicCourseInfo/{courseId}")
    public R getPublicCourseInfo(@PathVariable String courseId){
      CoursePublishVo coursePublishVo = service.publicCourseInfo(courseId);

      return R.ok().data("publicInfo",coursePublishVo);
    }

    //课程最终发布
    @ApiOperation(value = "课程最终发布")
    @PutMapping("/{courseId}")
    public R saveFinallyCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");//设置课程状态

        boolean b = service.updateById(eduCourse);
        if (b){
            return R.ok();
        }else {
            throw new GuliException(20001,"最终发布课程失败");
        }
    }
    @ApiOperation(value = "删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
       Boolean flag = service.removeCourse(courseId);
       if (flag){
           return R.ok();
       }else {
           throw new GuliException(20001,"删除失败");
       }
    }
}

