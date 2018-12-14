package org.hut.hutwhserviceconsumer.controller;

import java.util.Map;
import java.util.Date;

import com.alibaba.dubbo.config.annotation.Reference;
import org.hut.common.entity.SysDeptRelation;
import org.hut.openapi.user.service.SysDeptRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hut.common.constant.CommonConstant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;


/**
 * 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysDeptRelation")
public class SysDeptRelationController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysDeptRelationService sysDeptRelationService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDeptRelation
     */
    @GetMapping("/{id}")
    public R<SysDeptRelation> get(@PathVariable Integer id) {
        return new R<>(sysDeptRelationService.selectById(id));
    }


    /**
     * 分页查询信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @RequestMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysDeptRelationService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysDeptRelation 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysDeptRelation sysDeptRelation) {
        return new R<>(sysDeptRelationService.insert(sysDeptRelation));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysDeptRelationService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysDeptRelation 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysDeptRelation sysDeptRelation) {
        return new R<>(sysDeptRelationService.updateById(sysDeptRelation));
    }
}
