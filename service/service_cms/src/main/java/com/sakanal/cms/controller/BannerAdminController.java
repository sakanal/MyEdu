package com.sakanal.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.cms.entity.Banner;
import com.sakanal.cms.service.BannerService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-17
 */
@Api(tags = "Banner管理-管理员")
@Slf4j
@RestController
@RequestMapping("/cms/bannerAdmin")
public class BannerAdminController {
    @Resource
    private BannerService bannerService;

    //条件分页查询banner
    @ApiOperation("条件分页查询banner")
    @PostMapping("/pageBanner/{current}")
    public CommonResult<Page<Banner>> pageBanner(@PathVariable Long current){
        Page<Banner> bannerPage = new Page<>(current,10);
        bannerService.page(null);
        return new CommonResult<Page<Banner>>().SUCCESS(bannerPage);
    }

    //添加banner
    @PostMapping("/addBanner")
    public CommonResult<Boolean> addBanner(@RequestBody Banner banner){
        boolean flag = bannerService.save(banner);
        if (flag){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    //修改banner
    @ApiOperation("修改banner")
    @PostMapping("/updateBanner")
    public CommonResult<Boolean> updateBanner(@RequestBody Banner banner){
        boolean flag = bannerService.updateById(banner);
        if (flag){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    //根据id删除banner
    @ApiOperation("根据id删除banner")
    @DeleteMapping("/deleteBannerById/{id}")
    public CommonResult<Boolean> deleteBannerById(@PathVariable String id){
        boolean flag = bannerService.removeById(id);
        if (flag){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    //根据id查询banner
    @ApiOperation("根据id查询banner")
    @GetMapping("/getBannerById/{id}")
    public CommonResult<Banner> getBannerById(@PathVariable String id){
        Banner banner = bannerService.getById(id);
        if (banner!=null){
            return new CommonResult<Banner>().SUCCESS(banner);
        }else {
            return new CommonResult<Banner>().NO_RESULT_DATA();
        }
    }

}
