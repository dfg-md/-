package com.zzs.guli.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.ucenter.entity.UcenterMember;
import com.zzs.guli.ucenter.entity.vo.loginVo;
import com.zzs.guli.ucenter.entity.vo.registerVo;
import com.zzs.guli.ucenter.mapper.UcenterMemberMapper;
import com.zzs.guli.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzs.guli.ucenter.uitl.RegexUtils;
import com.zzs.guli.util.JwtUtils;
import com.zzs.guli.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-12-15
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String loginUser(loginVo user) {
        String phone = user.getMobile();
        if (RegexUtils.isPhoneInvalid(phone)){
            throw new GuliException(20001,"账号为【"+ phone +"】不符合规则");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)){
            throw new GuliException(20001,"密码不能为空");
        }

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",phone);
//        queryWrapper.eq("password",password);
        UcenterMember ucenterMember = this.baseMapper.selectOne(queryWrapper);
        System.out.println(ucenterMember);
        if (ucenterMember == null){
            throw new GuliException(20001,"账号密码错误或不存在");
        }
        //判断密码MD5.encrypt(password)
        if (!password.equals(ucenterMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }

        if (ucenterMember.getIsDisabled()){
            throw new GuliException(20001,"用户被禁用 请联系管理员 加秋秋号941560995");
        }
        //登录成功 生成toker
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        if ("".equals(jwtToken) || jwtToken == null){
            throw new GuliException(20001,"登录失败");
        }
        System.out.println(jwtToken);
        return jwtToken;
    }

    @Override
    public void register(registerVo user) {
        //注册数据
        String code = user.getCode();
        String phone = user.getMobile();
        String nickname = user.getNickname();
        String password = user.getPassword();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(nickname) ||
            StringUtils.isEmpty(code))  {
            throw new GuliException(20001,"账号密码不符合规则");
        }
        //判断手机号
        //查询手机号
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",phone);
        Integer integer = this.baseMapper.selectCount(queryWrapper);
        //判断手机号
        if (integer > 0 ){
            throw new GuliException(20001,"手机号已存在");
        }
        //
        String c =  redisTemplate.opsForValue().get(phone).toString();
        if (!c.equals(code)){
            throw new GuliException(20001,"验证码失效或不存在");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setPassword(password);
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(phone);
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("\n" +
                "https://edu-198626.oss-cn-beijing.aliyuncs.com/%E9%BB%98%E8%AE%A4/75ad32ce87c79d2a116bc9f75fbe8745.jpg");
        int insert = this.baseMapper.insert(ucenterMember);
        if (insert <= 0){
            throw new GuliException(20001,"注册失败");
        }

    }

    @Override
    public Integer countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
