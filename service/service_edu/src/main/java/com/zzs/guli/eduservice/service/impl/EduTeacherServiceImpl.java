package com.zzs.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzs.guli.eduservice.entity.EduTeacher;
import com.zzs.guli.eduservice.mapper.EduTeacherMapper;
import com.zzs.guli.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-09-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        baseMapper.selectPage(pageTeacher,wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long current = pageTeacher.getCurrent();

        boolean hasNext = pageTeacher.hasNext();
        boolean hasPrevious = pageTeacher.hasPrevious();
        // 把分页数据获取出来 放到map集合
       Map<String,Object> map =  new HashMap<>();
       map.put("total",total);
        map.put("pages",pages);
        map.put("size",size);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        map.put("records",records);
        return map;
    }

    @Override
    public boolean updateAudit(EduTeacher eduTeacher) {
        return false;
    }
}
