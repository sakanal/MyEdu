package com.sakanal.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakanal.cms.entity.Banner;
import com.sakanal.cms.mapper.BannerMapper;
import com.sakanal.cms.service.BannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-17
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Resource
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getAllBanner() {
        List<Banner> bannerList = this.list();
        if (bannerList.size()>0){
            return bannerList;
        }else {
            return null;
        }
    }

    @Override
    @Cacheable(key = "'getFrontBanner'",value = "banner")
    public List<Banner> getFrontBanner() {
        QueryWrapper<Banner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByDesc("id");
        bannerQueryWrapper.last("limit 2");
        List<Banner> bannerList = this.list(bannerQueryWrapper);
        if (bannerList.size()>0){
            return bannerList;
        }else {
            return null;
        }
    }
}
