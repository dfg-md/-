package com.zzs.guli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.eduservice.entity.EduSubject;
import com.zzs.guli.eduservice.entity.excel.SubjectData;
import com.zzs.guli.eduservice.entity.subject.OneSubject;
import com.zzs.guli.eduservice.entity.subject.TwoSubject;
import com.zzs.guli.eduservice.listener.SubjectEasyListener;
import com.zzs.guli.eduservice.mapper.EduSubjectMapper;
import com.zzs.guli.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-11-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {



    @Override
    public void saveSubject(MultipartFile file ,EduSubjectService service) {


        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectEasyListener(service))
                            .sheet()
                                    .doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getOneTwoSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<EduSubject> listOneSubject = baseMapper.selectList(wrapper);

        //查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();

        wrapperTwo.ne("parent_id",0);
        List<EduSubject> listTwoSubject = baseMapper.selectList(wrapperTwo);


        //存放最终数据
        List<OneSubject> finalListSubject = new ArrayList<>();
        //封装1级分类
        for (int i = 0; i < listOneSubject.size(); i++) {
            EduSubject eduSubject = listOneSubject.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalListSubject.add(oneSubject);
            //封装二级分类
            //存放一级分类中 的 二级分类属性
           List<TwoSubject> twoSubjects   = new ArrayList<>();
            for (int j = 0; j < listTwoSubject.size(); j++) {
                EduSubject tSubject =  listTwoSubject.get(j);
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }
            //最后把所有二级分类放到一级分类中
            oneSubject.setChildren(twoSubjects);
        }
        //封装二级分类
        return finalListSubject;
    }

    @Override
    public List<TwoSubject> getTwoSubject() {



        return null;
    }


}
