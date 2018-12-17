package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hut.common.dto.ProductAttrInfo;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.ProductAttribute;
import org.hut.openapi.user.service.pms.IProductAttributeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/12/17 11:43.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 商品属性管理API
 */

@Controller
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/api/v1/productAttribute")
public class PmsProductAttributeController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "商品属性名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public R getList(@PathVariable Long cid,
                     @RequestParam Map<String, Object> params,
                     @RequestParam(value = "type") Integer type) {
        return R.ok().put("page", productAttributeService.queryPage(params));
    }

    @ApiOperation("添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestBody ProductAttribute productAttribute) {
        if (productAttributeService.insert(productAttribute)) {
            return R.ok("添加商品【" + productAttribute.getName() + "】属性信息成功！");
        } else {
            return R.error("添加商品【" + productAttribute.getName() + "】属性信息失败！");
        }
    }

    @ApiOperation("修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable Long id, @RequestBody ProductAttribute productAttribute) {
        if (productAttributeService.updateById(productAttribute)) {
            return R.ok("修改商品【" + productAttribute.getName() + "】属性信息成功！");
        } else {
            return R.error("修改商品【" + productAttribute.getName() + "】属性信息失败！");
        }
    }

    @ApiOperation("查询单个商品属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R getItem(@PathVariable Long id) {
        return R.ok().put("prodictAttribute", productAttributeService.selectById(id));
    }

    @ApiOperation("批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public R delete(@RequestParam("ids") List<Long> ids) {
        if (productAttributeService.deleteBatchIds(ids)) {
            return R.ok("批量删除成功！");
        } else {
            return R.error("批量删除失败！");
        }
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public R getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return R.ok().put("productAttrInfoList", productAttrInfoList);
    }
}
