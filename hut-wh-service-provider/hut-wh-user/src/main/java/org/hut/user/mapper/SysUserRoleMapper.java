
package org.hut.user.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.hut.common.entity.SysUserRole;

/**
 * Created by hutwanghui on 2018/11/24 14:24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户角色接口
 */

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     */
    Boolean deleteByUserId(@Param("userId") Integer userId);
}