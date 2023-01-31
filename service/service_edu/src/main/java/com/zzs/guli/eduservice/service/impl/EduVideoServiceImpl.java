package com.zzs.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.client.VodClient;
import com.zzs.guli.eduservice.entity.EduVideo;
import com.zzs.guli.eduservice.mapper.EduVideoMapper;
import com.zzs.guli.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    @Autowired
    private VodClient client;


    @Override
    public List<EduVideo> getAllVideo(String videoId) {
        QueryWrapper<EduVideo> VideoQuery = new QueryWrapper<>();
        VideoQuery.eq("chapter_id",videoId);
        List<EduVideo> eduVideos = baseMapper.selectList(VideoQuery);
        if (eduVideos == null){
            throw new GuliException(20001,"查询数据不存在");
        }
        return eduVideos;
    }


    //同时删除视频
    @Override
    public boolean removeVideoById(String courseId) {
//        根据courseid 查询出所有视频id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_course_id");
        List<EduVideo> list = baseMapper.selectList(wrapper);
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EduVideo eduVideo = list.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        if (videoIds.size() > 0){
            client.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
         eduVideoQueryWrapper.eq("course_id", courseId);
        int i = baseMapper.delete(eduVideoQueryWrapper);
        if (i <= 0){
            return false;
        }
        return true;
    }
}
