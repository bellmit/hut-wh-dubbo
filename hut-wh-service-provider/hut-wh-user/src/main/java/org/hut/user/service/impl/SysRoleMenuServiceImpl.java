package org.hut.user.service.impl;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysRoleMenuMapper;
import org.hut.common.entity.SysRoleMenu;
import org.hut.openapi.user.service.SysRoleMenuService;
import org.springframework.cache.annotation.CacheEvict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24 20:10.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 决策菜单服务
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    @CacheEvict(value = "menu_details", key = "#role + '_menu'")
    public Boolean insertRoleMenus(String role, Integer roleId,String menuIds) {
        SysRoleMenu condition = new SysRoleMenu();
        condition.setRoleId(roleId);
        this.delete(new EntityWrapper<>(condition));

        if (StringUtils.isBlank(menuIds)){
            return Boolean.TRUE;
        }

        if (StringUtils.isBlank(menuIds)) {
            return Boolean.TRUE;
        }

        List<SysRoleMenu> roleMenuList = new ArrayList<>();
        List<String> menuIdList = Arrays.asList(menuIds.split(","));

        for (String menuId : menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(Integer.valueOf(menuId));
            roleMenuList.add(roleMenu);
        }
        return this.insertBatch(roleMenuList);
    }
}
