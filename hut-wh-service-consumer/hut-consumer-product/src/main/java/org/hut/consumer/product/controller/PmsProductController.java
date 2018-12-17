package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.ProductParam;
import org.hut.openapi.user.service.pms.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/12/17 14:23.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 商品管理API
 */

@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/api/v1/product")
public class PmsProductController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IProductService productService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestBody ProductParam productParam) {
        if (productService.insertProductWithOtherList(productParam)) {
            return R.ok("创建商品【" + productParam.getName() + "】成功！");
        } else {
            return R.ok("创建商品【" + productParam.getName() + "】失败！");
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R getUpdateInfo(@PathVariable Long id) {
        return R.ok().put("product", productService.selectById(id));
    }

    @ApiOperation("更新商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable Long id, @RequestBody ProductParam productParam, BindingResult bindingResult) {
        if (productService.updateProductWithOtherList(id, productParam)) {
            return R.ok("更新商品【" + productParam.getName() + "】成功！");
        } else {
            return R.ok("更新商品【" + productParam.getName() + "】失败！");
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "publishStatus", value = "上架状态", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "verifyStatus", value = "新品状态", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "关键字", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "productSn", value = "货号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "productCategoryId", value = "商品属性ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "brandId", value = "品牌ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = true, defaultValue = "1",dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页条数", required = true,defaultValue = "10",dataType = "Integer")
    })
    public R getList(@RequestParam Map<String, Object> params) {
        return R.ok().put("page", productService.queryPage(params));
    }


    @ApiOperation("批量修改审核状态")
    @RequestMapping(value = "/update/verifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("verifyStatus") Integer verifyStatus,
                                     @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error("还未实现。。。。");
        }
    }

    @ApiOperation("批量上下架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error("还未实现。。。。");
        }
    }

    @ApiOperation("批量推荐商品")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error("还未实现。。。。");
        }
    }

    @ApiOperation("批量设为新品")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateNewStatus(@RequestParam("ids") List<Long> ids,
                                  @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error("还未实现。。。。");
        }
    }

    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return R.ok();
        } else {
            return R.error("还未实现。。。。");
        }
    }
}
