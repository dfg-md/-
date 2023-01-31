package com.zzs.guli.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.eduservice.entity.EduSubject;
import com.zzs.guli.eduservice.entity.excel.SubjectData;
import com.zzs.guli.eduservice.service.EduSubjectService;

import java.util.Map;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-11-18 16:13
 **/
public class SubjectEasyListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService service;

    public SubjectEasyListener(EduSubjectService service) {
        this.service = service;
    }

    public SubjectEasyListener() {
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext analysisContext) {
        if (data == null){
            throw  new GuliException(20001,"文件数据为空");
        }
        EduSubject eduSubject = this.existOneSubject(service, data.getOneSubjectName());
        if (eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(data.getOneSubjectName());
            service.save(eduSubject);
        }
        String pid = eduSubject.getId();

        EduSubject eduSubjectTwo = this.existTwoSubject(service,data.getTwoSubjectName(),pid);
        if (eduSubjectTwo ==null){
            eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setParentId(pid);
            eduSubjectTwo.setTitle(data.getTwoSubjectName());
            service.save(eduSubjectTwo);
        }
    }

    public EduSubject existOneSubject(EduSubjectService service , String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = service.getOne(wrapper);
        return oneSubject;
    }


    public EduSubject existTwoSubject(EduSubjectService service , String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject oneSubject = service.getOne(wrapper);
        return oneSubject;
    }


    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) { }
}
