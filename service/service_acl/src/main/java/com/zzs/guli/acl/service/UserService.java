package com.zzs.guli.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzs.guli.acl.entity.User;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户号查询用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据用户id修改用户状态
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


    User selectOneByName(String admin);
}
