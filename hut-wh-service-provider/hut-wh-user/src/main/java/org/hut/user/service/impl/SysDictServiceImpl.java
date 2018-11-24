package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysDictMapper;
import org.hut.common.entity.SysDict;
import org.hut.openapi.user.service.SysDictService;

/**
 * Created by hutwanghui on 2018/11/24 15:42.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 字典服务实现
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

}
