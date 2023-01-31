package com.zzs.guli.eduservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: StarSea99
 * @Date: 2020-10-21 12:43
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @TableField("teacher_id")
    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @TableField("subject_parent_id")
    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @TableField("subject_id")
    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

}
