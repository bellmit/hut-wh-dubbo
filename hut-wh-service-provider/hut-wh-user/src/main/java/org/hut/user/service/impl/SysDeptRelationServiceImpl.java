package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysDeptRelationMapper;
import org.hut.user.entity.SysDeptRelation;
import org.hut.user.service.SysDeptRelationService;

/**
 * Created by hutwanghui on 2018/11/24 15:41.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 部门关系服务实现类
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelation> implements SysDeptRelationService {

}
