package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hut.common.dto.ProductCategoryParam;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.ProductCategoryWithChildrenItem;
import org.hut.openapi.user.service.pms.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/12/17 12:05.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:商品分类模块API
 */

@Controller
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/api/v1/productCategory")
public class PmsProductCategoryController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IProductCategoryService productCategoryService;

    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestBody ProductCategoryParam productCategoryParam) {
        if (productCategoryService.insertProductCategoryParam(productCategoryParam)) {
            return R.ok("添加产品分类【" + productCategoryParam.getName() + "】成功！");
        } else {
            return R.error("添加产品分类【" + productCategoryParam.getName() + "】失败！");
        }
    }

    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable Long id,
                    @RequestBody ProductCategoryParam productCategoryParam,
                    BindingResult result) {
        if (productCategoryService.updateProductCategoryParam(id, productCategoryParam)) {
            return R.ok("修改商品分类【" + productCategoryParam.getName() + "】成功！");
        } else {
            return R.error("修改商品分类【" + productCategoryParam.getName() + "】失败！");
        }
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public R getList(@PathVariable Long parentId,
                          @RequestParam Map<String, Object> params,
                          @RequestParam(value = "type") Integer type,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return R.ok().put("page", productCategoryService.queryPage(params));
    }

    @ApiOperation("根据id获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R getItem(@PathVariable Long id) {
        return R.ok().put("productCategory", productCategoryService.selectById(id));
    }

    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R delete(@PathVariable Long id) {
        if (productCategoryService.deleteById(id)) {
            return R.ok("删除商品分类" + id + "成功！");
        } else {
            return R.error("删除商品分类" + id + "失败！");
        }
    }

    @ApiOperation("修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        if (productCategoryService.updateNavStatusBeatch(ids, navStatus)) {
            return R.ok("修改导航栏显示状态成功！");
        } else {
            return R.error("修改导航栏显示状态失败");
        }
    }

    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        if (productCategoryService.updateShowStatusBeatch(ids, showStatus)) {
            return R.ok("修改分类显示状态成功！");
        } else {
            return R.error("修改分类显示状态失败");
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public R listWithChildren() {
        List<ProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return R.ok().put("productCategoryWithChildrenItemList", list);
    }
}
