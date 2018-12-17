package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.ProductAttributeCategory;
import org.hut.openapi.user.service.pms.IProductAttributeCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by hutwanghui on 2018/12/17 11:17.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:商品属性分类API
 */

@Controller
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/api/v1/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestParam String name) {
        if (productAttributeCategoryService.insert(ProductAttributeCategory.builder().name(name).build())) {
            return R.ok("添加商品属性分类【" + name + "】成功！");
        } else {
            return R.error("添加商品属性分类【" + name + "失败！");
        }
    }

    @ApiOperation("修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable Long id, @RequestParam String name) {
        if (productAttributeCategoryService.updateById(ProductAttributeCategory.builder().id(id).name(name).build())) {
            return R.ok("修改商品属性分类【" + name + "】成功！");
        } else {
            return R.error("修改商品属性分类【" + name + "失败！");
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R delete(@PathVariable Long id) {
        if (productAttributeCategoryService.deleteById(ProductAttributeCategory.builder().id(id).build())) {
            return R.ok("删除单个商品属性分类成功！");
        } else {
            return R.error("删除单个商品属性分类失败！");
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R getItem(@PathVariable Long id) {
        ProductAttributeCategory productAttributeCategory = productAttributeCategoryService.selectById(id);
        return R.ok().put("productAttributeCategory", productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "商品分类关键字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页条数", required = true, dataType = "Integer")
    })
    public R getList(@RequestParam Map<String, Object> params,
                     @RequestParam(value = "keyword", required = false) String keyword) {
        return R.ok().put("page", productAttributeCategoryService.queryPage(params));
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public R getListWithAttr() {
        return new R(productAttributeCategoryService.selectList(new EntityWrapper<ProductAttributeCategory>()));
    }
}
