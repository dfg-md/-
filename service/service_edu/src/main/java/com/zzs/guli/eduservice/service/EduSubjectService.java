package com.zzs.guli.eduservice.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzs.guli.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzs.guli.eduservice.entity.subject.OneSubject;
import com.zzs.guli.eduservice.entity.subject.TwoSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-11-18
 */
public interface EduSubjectService extends IService<EduSubject>   {

    void saveSubject(MultipartFile file,EduSubjectService service);

    List<OneSubject> getOneTwoSubject();

    List<TwoSubject> getTwoSubject();
}
