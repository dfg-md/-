package com.zzs.guli.eduservice.controller;


import com.zzs.guli.eduservice.entity.EduChapter;
import com.zzs.guli.eduservice.entity.chapter.ChapterVo;
import com.zzs.guli.eduservice.service.EduChapterService;
import com.zzs.guli.util.R;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/eduService/chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService service;

    @GetMapping("getChapterInfo/{courseId}")
    public R getChapter(@PathVariable String courseId){
        List<ChapterVo> list   = service.getChapterVideoByCourseId(courseId);
        return R.ok().data("list",list);
    }


    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
        System.out.println("+++++++++"+chapter);
        boolean save = service.save(chapter);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }


    /**
     * 根据id 查询课程
     * @param chapterId
     * @return
     */
   @GetMapping("getChapterInfoById/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
       EduChapter byId = service.getById(chapterId);
       return R.ok().data("list",byId);
   }


    /**
     * 根据id 修改课程
     * @param educhapter
     * @return
     */
   @PutMapping("updateChapterById")
    public R deleteChapter(@RequestBody EduChapter educhapter){
       boolean b = service.updateById(educhapter);
       if (b){
           return R.ok();
       }else {
           return R.error();
       }
   }

   @DeleteMapping("deleteChapterById/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
       service.deleteChapterById(chapterId);
       return R.ok();
   }

}

