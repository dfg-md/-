package com.zzs.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.bind.v2.TODO;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.entity.EduCourse;
import com.zzs.guli.eduservice.entity.EduCourseDescription;
import com.zzs.guli.eduservice.entity.EduTeacher;
import com.zzs.guli.eduservice.entity.frontVo.CourseFrontVo;
import com.zzs.guli.eduservice.entity.frontVo.CourseWebVo;
import com.zzs.guli.eduservice.entity.vo.CourseInfoVo;
import com.zzs.guli.eduservice.entity.vo.CoursePublishVo;
import com.zzs.guli.eduservice.entity.vo.CourseQuery;
import com.zzs.guli.eduservice.mapper.EduCourseMapper;
import com.zzs.guli.eduservice.service.EduChapterService;
import com.zzs.guli.eduservice.service.EduCourseDescriptionService;
import com.zzs.guli.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {



    @Autowired
    private EduCourseDescriptionService service;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduVideoService videoService;

    /**
     * //添加课程信息 方法
     * @param courseInfoVo
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0){
            throw  new GuliException(20001,"添加课程信息失败");
        }
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        boolean save = service.save(eduCourseDescription);
        if (!save){
            throw  new GuliException(20001,"添加课程信息失败");
        }
        return cid;
    }

    @Override
    public CourseInfoVo getCourseByid(String courseId) {
        if (courseId == null || courseId ==""){
            return null;
        }
        //查询课程表
        EduCourse eduCourse = this.baseMapper.selectById(courseId);
        if (eduCourse == null){
            return null;
        }
        EduCourseDescription byId = service.getById(courseId);
        //查询简介表
        if (byId == null){
            return null;
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(byId.getDescription());
        return courseInfoVo;
    }

    @Override
    public void upDateCourse(CourseInfoVo courseInfoVo) {
        //健壮性判断
        if (courseInfoVo == null){
            throw new GuliException(20001,"修改课程信息失败");
        }
        // 拆分成两个表
        //第一表就是EduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = this.baseMapper.updateById(eduCourse);
        if (i < 0){
            throw new GuliException(20001,"修改课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        boolean b = service.updateById(eduCourseDescription);
        if (b == false){
            throw new GuliException(20001,"修改课程信息失败");
        }
    }

    @Override
    public CoursePublishVo publicCourseInfo(String courseId) {

        CoursePublishVo publicCourseInfo = baseMapper.getPublicCourseInfo(courseId);
        if (publicCourseInfo == null){
            throw new GuliException(20001,"课程id 不存在");
        }
        return publicCourseInfo;
    }

    @Override
    public IPage pageCourseCondition(Long current, Long limit, CourseQuery courseQuery) {


        Page<EduCourse> pageCourse = new Page<>(current,limit);

        QueryWrapper<EduCourse> eduCourseWrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String subjectId = courseQuery.getSubjectId();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        //判断 title  是否为空
        if (!StringUtils.isEmpty(title)){
            eduCourseWrapper.eq("title",title);
        }
        //判断 status 是否为空
        if (!StringUtils.isEmpty(status)){
            eduCourseWrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(subjectId)){
            eduCourseWrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(teacherId)){
            eduCourseWrapper.eq("teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            eduCourseWrapper.eq("subject_parent_id",subjectParentId);
        }
        //排序
        eduCourseWrapper.orderByDesc("gmt_create");

        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(pageCourse, eduCourseWrapper);

        if (eduCourseIPage==null){
            throw new GuliException(20001,"数据为空");
        }

        return eduCourseIPage;
    }

    @Override
    public Boolean removeCourse(String courseId) {

        //判断值是否为空
        if ("".equals(courseId) || courseId == null){
            throw new GuliException(20001,"课程id不存在,请重试");
        }


        //根据课程id 删除小节
        boolean b = videoService.removeVideoById(courseId);
        if (!b){
            throw new GuliException(20001,"课程id不存在,无法删除小节,请重试");
        }
        //根据课程id 删除描述


        boolean b1 = service.removeDescriptionById(courseId);
        if (!b1){
            throw new GuliException(20001,"课程id不存在,无法删除简介,请重试");
            //TODO
            /**
             * 删除失败需要回滚数据 因为此时小节已经删除
             */
        }


        // 根据课程id 删除章节
        Boolean b2  = chapterService.removeChapterById(courseId);
        if (!b2){
            throw new GuliException(20001,"课程id不存在,无法删除简介,请重试");
            //TODO
            /**
             * 删除失败需要回滚数据 因为此时小节，描述已经删除
             */
        }

        //根据课程id删除课程本身
        int i = baseMapper.deleteById(courseId);
        if (i <= 0){
            throw new GuliException(20001,"课程id不存在,无法删除课程,请重试");
            //TODO
            /**
             * 删除失败需要回滚数据 因为此时小节,简介,章节已经删除
             */
        }


        /**
         * mybatis-plus 已经加上事务
         */
        return true;
    }

    @Override
    public Map<String, Object> getPage(Page<EduCourse> pageCourse, CourseFrontVo vo) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(vo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",vo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(vo.getSubjectId())){
            wrapper.eq("subject_id",vo.getSubjectId());
        }
        if (!StringUtils.isEmpty(vo.getBuyCountSort())){
            wrapper.eq("buy_count",vo.getBuyCountSort());
        }
        if (!StringUtils.isEmpty(vo.getGmtCreateSort())){
            wrapper.eq("gmt_create",vo.getGmtCreateSort());
        }
        if (!StringUtils.isEmpty(vo.getPriceSort())){
            wrapper.eq("price",vo.getPriceSort());
        }

         baseMapper.selectPage(pageCourse, wrapper);


        List<EduCourse> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long current = pageCourse.getCurrent();

        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();
        // 把分页数据获取出来 放到map集合
        Map<String,Object> map =  new HashMap<>();
        map.put("total",total);
        map.put("pages",pages);
        map.put("size",size);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("items",records);
        return map;

    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
        return  baseMapper.getBase(id);
    }

}
