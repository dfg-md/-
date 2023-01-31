package com.zzs.guli.ucenter.service;

import com.zzs.guli.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzs.guli.ucenter.entity.vo.loginVo;
import com.zzs.guli.ucenter.entity.vo.registerVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-12-15
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String loginUser(loginVo user);

    void register(registerVo user);

    Integer countRegister(String day);
}
