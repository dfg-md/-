package com.zzs.guli.cms.service.impl;


import com.zzs.guli.base.ExceptionHandler.GuliException;
import com.zzs.guli.cms.entity.CrmBanner;
import com.zzs.guli.cms.mapper.CrmBannerMapper;
import com.zzs.guli.cms.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-12-14
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CmsBannerService {


    @Override
    @Cacheable(value = "banner",key="'selectIndexList'")
    public List<CrmBanner> selectAllBanner() {
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        if (crmBanners==null){
            throw new GuliException(20001,"查询所有错误");
        }
        return crmBanners;
    }
}
