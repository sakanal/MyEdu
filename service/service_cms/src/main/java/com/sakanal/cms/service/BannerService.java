package com.sakanal.cms.service;

import com.sakanal.cms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-17
 */
public interface BannerService extends IService<Banner> {

    List<Banner> getAllBanner();

    List<Banner> getFrontBanner();
}
