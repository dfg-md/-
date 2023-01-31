package com.zzs.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.eduservice.entity.EduCourseDescription;
import com.zzs.guli.eduservice.entity.EduVideo;
import com.zzs.guli.eduservice.mapper.EduCourseDescriptionMapper;
import com.zzs.guli.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-11-27
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public boolean removeDescriptionById(String courseId) {
        QueryWrapper<EduCourseDescription> DescriptionQueryWrapper = new QueryWrapper<>();
        DescriptionQueryWrapper.eq("id", courseId);
        int i = baseMapper.delete(DescriptionQueryWrapper);
        if (i <= 0){
            return false;
        }
        return true;
    }
}
