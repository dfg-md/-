package com.zzs.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.entity.EduChapter;
import com.zzs.guli.eduservice.entity.EduCourseDescription;
import com.zzs.guli.eduservice.entity.EduVideo;
import com.zzs.guli.eduservice.entity.chapter.ChapterVo;
import com.zzs.guli.eduservice.entity.chapter.VideoVo;
import com.zzs.guli.eduservice.mapper.EduChapterMapper;
import com.zzs.guli.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService service;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //查询章节
        QueryWrapper<EduChapter> wrapper  = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        //查询小节
        QueryWrapper<EduVideo> wrapperVideo  = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> listVideo = service.list(wrapperVideo);

        //创建存放最终数据
        List<ChapterVo> finallyList = new ArrayList<>();


        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finallyList.add(chapterVo);
            //创建集合封装 小节
            ArrayList<VideoVo> Videovo = new ArrayList<>();
            //遍历小节
            for (int h = 0; h < listVideo.size(); h++) {
                //获取每一个小节
                EduVideo eduVideo = listVideo.get(h);
                //如果相等 那么 就相当于这个小节是这个课程的 就copy  存到 videoVo 集合中
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    Videovo.add(videoVo);
                }
            }
            chapterVo.setChildren(Videovo);
        }
        return finallyList;
    }

    @Override
    public void deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> QueryEduVideo = new QueryWrapper<>();
        QueryEduVideo.eq("chapter_id",chapterId);
        int count = service.count(QueryEduVideo);

        if (count > 0){
            throw new GuliException(20001,"课程删除失败 还有小节未删除");
        }else {

            int i = baseMapper.deleteById(chapterId);
        }
    }

    @Override
    public Boolean removeChapterById(String courseId) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id", courseId);
        int i = baseMapper.delete(eduChapterQueryWrapper);
        if (i <= 0){
            return false;
        }
        return true;
    }


}
