package com.zzs.guli.cms.controller;

import com.zzs.guli.cms.service.CmsBannerService;
import com.zzs.guli.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-14 18:43
 **/

@RestController
@RequestMapping("/cmsService/bannerFront")
//@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CmsBannerService service;

    @GetMapping()
    public R getAllBanner(){
        return R.ok().data("list",service.selectAllBanner());
    }
}
