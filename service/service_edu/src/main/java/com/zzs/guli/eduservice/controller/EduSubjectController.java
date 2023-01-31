package com.zzs.guli.eduservice.controller;


import com.zzs.guli.eduservice.entity.subject.OneSubject;
import com.zzs.guli.eduservice.entity.subject.TwoSubject;
import com.zzs.guli.eduservice.service.EduSubjectService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/eduService/subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService service ;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //MultipartFile 能获取上传文件
        service.saveSubject(file,service);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> L = service.getOneTwoSubject();
        return R.ok().data("list",L);
    }

    @GetMapping("getTwoSubject")
    public R getTwoSubject(){
      List<TwoSubject> subject = service.getTwoSubject();
        return R.ok();
    }

}

