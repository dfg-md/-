package com.zzs.guli.eduservice.controller;

import com.zzs.guli.util.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: StarSea99
 * @Date: 2020-10-04 14:25
 */
@RestController
@RequestMapping("/eduService/admin")
//@CrossOrigin  //解决跨域
public class EduLoginController {

    /**
     * 用户登录
     * @return
     */
    @GetMapping("/login")
    public R login() {
        System.out.println(22222);
        return R.ok().data("token","admin");
    }

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/info")
    public R info() {
        System.out.println(22222);
        return R.ok() .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
