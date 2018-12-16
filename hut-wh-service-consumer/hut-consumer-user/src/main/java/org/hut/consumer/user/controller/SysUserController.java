package org.hut.consumer.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hut.common.constant.CommonConstant;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;
import org.hut.common.entity.SysUser;
import org.hut.openapi.user.service.SysUserService;

import java.util.Map;
import org.springframework.web.bind.annotation.*;
/**
 * 用户表 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserService sysUserService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysUser
     */
    @GetMapping("/{id}")
    public R<SysUser> get(@PathVariable Integer id) {
        return new R<>(sysUserService.selectById(id));
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
        return sysUserService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysUser 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysUser sysUser) {
        sysUser.setPassword(new Md5Hash(new Md5Hash(sysUser.getPassword(), sysUser.getUsername())).toString());
        sysUser.setSalt(sysUser.getUsername());
        return new R<>(sysUserService.insert(sysUser));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysUserService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysUser 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysUser sysUser) {
        return new R<>(sysUserService.updateById(sysUser));
    }
}
