package com.zzs.guli.eduservice.service;

import com.zzs.guli.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzs.guli.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    void deleteChapterById(String chapterId);

    Boolean removeChapterById(String courseId);
}
