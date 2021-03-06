package org.hut.consumer.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.constant.CommonConstant;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;
import org.hut.common.entity.SysMenu;
import org.hut.openapi.user.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * 菜单权限表 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysMenuService sysMenuService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysMenu
     */
    @GetMapping("/{id}")
    public R<SysMenu> get(@PathVariable Integer id) {
        return new R<>(sysMenuService.selectById(id));
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
        return sysMenuService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysMenu 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysMenu sysMenu) {
        return new R<>(sysMenuService.insert(sysMenu));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysMenuService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysMenu 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(new Date());
        return new R<>(sysMenuService.updateById(sysMenu));
    }
}
