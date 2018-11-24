package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysRoleDeptMapper;
import org.hut.user.entity.SysRoleDept;
import org.hut.user.service.SysRoleDeptService;

/**
 * Created by hutwanghui on 2018/11/24 20:09.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 部门和角色对应关系
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {
	
}
