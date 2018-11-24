
package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysUserRoleMapper;
import org.hut.user.entity.SysUserRole;
import org.hut.user.service.SysUserRoleService;

/**
 * Created by hutwanghui on 2018/11/24 20:10.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 用户角色服务
 */
@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     */
    @Override
    public Boolean deleteByUserId(Integer userId) {
        return baseMapper.deleteByUserId(userId);
    }
}
