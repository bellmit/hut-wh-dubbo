package org.hut.consumer.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.constant.CommonConstant;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;
import org.hut.common.entity.SysUserRole;
import org.hut.openapi.user.service.SysUserRoleService;

import java.util.Map;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色表 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserRoleService sysUserRoleService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysUserRole
     */
    @GetMapping("/{id}")
    public R<SysUserRole> get(@PathVariable Integer id) {
        return new R<>(sysUserRoleService.selectById(id));
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
        return sysUserRoleService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysUserRole 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysUserRole sysUserRole) {
        return new R<>(sysUserRoleService.insert(sysUserRole));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysUserRoleService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysUserRole 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysUserRole sysUserRole) {
        return new R<>(sysUserRoleService.updateById(sysUserRole));
    }
}
