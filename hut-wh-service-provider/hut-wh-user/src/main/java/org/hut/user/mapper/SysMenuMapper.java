

package org.hut.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.hut.common.vo.MenuVO;
import org.hut.common.entity.SysMenu;

import java.util.List;


/**
 * Created by hutwanghui on 2018/11/24 14:25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:菜单权限接口
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleName(@Param("role") String role);
}