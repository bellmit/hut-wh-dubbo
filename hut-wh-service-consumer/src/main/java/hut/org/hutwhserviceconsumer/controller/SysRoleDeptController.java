package hut.org.hutwhserviceconsumer.controller;

import java.util.Map;
import java.util.Date;

import com.alibaba.dubbo.config.annotation.Reference;
import org.hut.common.entity.SysRoleDept;
import org.hut.openapi.user.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hut.common.constant.CommonConstant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;


/**
 * 角色与部门对应关系 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysRoleDept")
public class SysRoleDeptController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysRoleDept
     */
    @GetMapping("/{id}")
    public R<SysRoleDept> get(@PathVariable Integer id) {
        return new R<>(sysRoleDeptService.selectById(id));
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
        return sysRoleDeptService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysRoleDept 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysRoleDept sysRoleDept) {
        return new R<>(sysRoleDeptService.insert(sysRoleDept));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysRoleDeptService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysRoleDept 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysRoleDept sysRoleDept) {
        return new R<>(sysRoleDeptService.updateById(sysRoleDept));
    }
}
