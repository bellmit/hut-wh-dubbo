package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.Brand;
import org.hut.openapi.user.service.pms.IBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/12/17 11:16.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 商品品牌API
 */

@Controller
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/api/v1/brand")
public class PmsBrandController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public R getList() {
        return R.ok().put("allBrand", brandService.selectList(new EntityWrapper<Brand>().orderBy("id", true)));
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R create(@RequestBody Brand pmsBrand, BindingResult result) {
        if (brandService.insert(pmsBrand)) {
            return R.ok("添加【" + pmsBrand.getName() + "】品牌成功！");
        } else {
            return R.error("添加【" + pmsBrand.getName() + "】品牌失败！");
        }
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable("id") Long id,
                    @RequestBody Brand pmsBrandParam,
                    BindingResult result) {
        if (brandService.updateById(pmsBrandParam)) {
            return R.ok("修改【" + id + "】品牌成功");
        } else {
            return R.error("修改【" + id + "】品牌失败");
        }
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R delete(@PathVariable("id") Long id) {
        if (brandService.deleteById(id)) {
            return R.ok("删除【" + id + "】品牌成功");
        } else {
            return R.error("删除【" + id + "】品牌失败");
        }
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表-兼容MB和MB-PLUS")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "品牌名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "品牌关键字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页条数", required = true, dataType ="Integer")
    })
    public R getList(@RequestParam Map<String, Object> params,
                     @RequestParam(value = "keyword", required = false) String keyword) {
        return R.ok().put("page", brandService.queryPage(params));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public R getItem(@PathVariable("id") Long id) {
        Brand b = brandService.selectById(id);
        if (b != null) {
            return R.ok().put("brand", b);
        }
        return R.error("查询失败");
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public R deleteBatch(@RequestParam("ids") List<Long> ids) {
        //TODO 批量删除失败情况
        if (brandService.deleteBatchIds(ids)) {
            return R.ok("批量删除" + ids + "成功！");
        } else {
            return R.error("批量删除" + ids + "失败！");
        }
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateShowStatus(@RequestParam("ids") List<Long> ids,
                              @RequestParam("showStatus") Integer showStatus) {

        if (brandService.updateBrandStatusBatchIds(ids, showStatus, "brand")) {
            return R.ok("批量修改【" + ids + "】等商品的显示【" + showStatus + "】状态");
        } else {
            return R.error("批量修改失败！");
        }
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    @ResponseBody
    public R updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                 @RequestParam("factoryStatus") Integer factoryStatus) {
        if (brandService.updateBrandStatusBatchIds(ids, factoryStatus, "factory")) {
            return R.ok("批量修改【" + ids + "】等商品的厂家制造商【" + factoryStatus + "】状态");
        } else {
            return R.error("批量修改失败！");
        }
    }
}
