package hut.org.hutwhserviceconsumer.controller;

import java.util.Map;
import java.util.Date;

import com.alibaba.dubbo.config.annotation.Reference;
import org.hut.common.entity.SysDept;
import org.hut.openapi.user.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hut.common.constant.CommonConstant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.hut.common.entity.Query;
import org.hut.common.entity.R;


/**
 * 部门管理 前端控制器
 *
 * @author hutwanghui
 * @since 2018-11-24
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController {
    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysDeptService sysDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/{id}")
    public R<SysDept> get(@PathVariable Integer id) {
        return new R<>(sysDeptService.selectById(id));
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
        return sysDeptService.selectPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 添加
     *
     * @param sysDept 实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> add(@RequestBody SysDept sysDept) {
        return new R<>(sysDeptService.insert(sysDept));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return new R<>(sysDeptService.deleteById(id));
    }

    /**
     * 编辑
     *
     * @param sysDept 实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> edit(@RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(new Date());
        return new R<>(sysDeptService.updateById(sysDept));
    }
}
