package hut.org.hutwhserviceconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.constant.CommonConstant;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;
import org.hut.common.entity.SysRole;
import org.hut.openapi.user.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysRoleService sysRoleService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysRole
     */
    @GetMapping("/{id}")
    public R<SysRole> get(@PathVariable Integer id) {
        return new R<>(sysRoleService.selectById(id));
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
        return sysRoleService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysRole 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysRole sysRole) {
        return new R<>(sysRoleService.insert(sysRole));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysRoleService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysRole 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysRole sysRole) {
        sysRole.setUpdateTime(new Date());
        return new R<>(sysRoleService.updateById(sysRole));
    }
}
