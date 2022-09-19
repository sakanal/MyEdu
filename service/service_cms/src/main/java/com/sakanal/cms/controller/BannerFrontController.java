package com.sakanal.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.cms.entity.Banner;
import com.sakanal.cms.service.BannerService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "Banner管理-用户")
@Slf4j
@RestController
@RequestMapping("/cms/bannerUser")
public class BannerFrontController {
    @Resource
    private BannerService bannerService;

    @ApiOperation("用户获取Banner")
    @GetMapping("/getAllBanner")
    public CommonResult<List<Banner>> getAllBanner(){
        List<Banner> bannerList =bannerService.getFrontBanner();

        if (bannerList.size()>0){
            return new CommonResult<List<Banner>>().SUCCESS(bannerList);
        }else {
            return new CommonResult<List<Banner>>().NO_RESULT_DATA();
        }
    }
}
