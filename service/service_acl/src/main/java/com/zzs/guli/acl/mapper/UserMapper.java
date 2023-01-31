package com.zzs.guli.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzs.guli.acl.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户id修改用户状态
     *
     * @param user
     * @return
     */
    int changeStatus(User user);

    /**
     * 删除用户角色表
     * @param id
     * @return
     */
    int deleteUserRole(String id);

//    @Results(id="bookssss",value= @Result(property ="bookPhoto"  ,column = "bookimg" ))
    @Select("select * from acl_user WHERE  username = #{admin}")
    User selectOneByName(String admin);
}
