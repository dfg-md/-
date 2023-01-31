package com.zzs.guli.acl.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-09 19:39
 **/
@Data
public class userVo {

    @ApiModelProperty(value = "角色id")
    private String userid;

    private String username;

    private String password;

}
