package com.zzs.guli.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description:
 * @Author: dfg
 * @Date:
 */
@ApiModel(value = "Subject查询数据", description = "课程分类列表")
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
