package org.hut.consumer.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hut.common.entity.R;
import org.hut.common.entity.pms.SkuStock;
import org.hut.openapi.user.service.pms.ISkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hutwanghui on 2018/12/17 16:03.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 库存管理API
 */

@Controller
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/api/v1/sku")
public class PmsSkuStockController {
    @Reference(
            version = "1.0.0",
            group = "pms",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private ISkuStockService skuStockService;

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public R getList(@PathVariable Long pid, @RequestParam(value = "keyword", required = false) String keyword) {
        List<SkuStock> skuStockList = skuStockService.selectByProductIdAndKeyWord(pid, keyword);
        return R.ok().put("skuStockList", skuStockList);
    }

    @ApiOperation("批量更新库存信息")
    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    @ResponseBody
    public R update(@PathVariable Long pid, @RequestBody List<SkuStock> skuStockList) {

        if (skuStockService.updateBatchByProductId(pid, skuStockList)) {
            return R.ok("批量更新库存信息成功！");
        } else {
            return R.error("批量更新库存信息失败！");
        }
    }
}
