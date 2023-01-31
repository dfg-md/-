package com.zzs.demo.controllerText;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.eduservice.entity.EduChapter;
import com.zzs.guli.eduservice.mapper.EduChapterMapper;

import org.junit.Test;

import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-11-30 15:23
 **/

public class chapterTest extends ServiceImpl<EduChapterMapper, EduChapter>  {


    @Test
    public void Test(){
         String  courseId ="1192252213659774977";
        QueryWrapper<EduChapter> wrapper  = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = this.baseMapper.selectList(wrapper);


        System.out.println(eduChapters);
    }



}
