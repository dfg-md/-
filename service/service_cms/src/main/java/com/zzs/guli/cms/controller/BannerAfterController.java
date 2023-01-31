package com.zzs.guli.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzs.guli.cms.entity.CrmBanner;
import com.zzs.guli.cms.service.CmsBannerService;
import com.zzs.guli.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/cmsService/bannerAdmin")
//@CrossOrigin
public class BannerAfterController {


    @Autowired
    private CmsBannerService service;

    @GetMapping("/{page}/{limit}")
    public R selectPage(@PathVariable long page  ,
                        @PathVariable long limit){
        Page<CrmBanner> page1 = new Page<>(page,limit);
        service.page(page1, null);

        return R.ok().data("items",page1.getRecords()).data("total",page1.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = service.getById(id);
        return R.ok().data("item", banner);

    }

    @CacheEvict(value = "banner", allEntries=true)
    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner banner) {
        boolean save = service.save(banner);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @CacheEvict(value = "banner", allEntries=true)
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        boolean b = service.updateById(banner);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }

    }

    @CacheEvict(value = "banner", allEntries=true)
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("{id}")
    public R remove(@PathVariable String id) {
        boolean b = service.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }

    }


}

