package com.zzs.guli.cms.service;

import com.zzs.guli.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-12-14
 */
public interface CmsBannerService extends IService<CrmBanner> {


    List<CrmBanner> selectAllBanner();
}
