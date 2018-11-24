

package org.hut.openapi.user.service;


import com.baomidou.mybatisplus.service.IService;
import org.hut.common.entity.SysUserRole;

/**
 * <p>
 * 用户角色表 服务类
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     */
    Boolean deleteByUserId(Integer userId);
}
