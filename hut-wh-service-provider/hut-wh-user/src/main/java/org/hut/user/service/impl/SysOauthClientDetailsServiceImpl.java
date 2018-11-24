package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysOauthClientDetailsMapper;
import org.hut.user.entity.SysOauthClientDetails;
import org.hut.user.service.SysOauthClientDetailsService;

/**
 * Created by hutwanghui on 2018/11/24 20:09.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 第三方
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements SysOauthClientDetailsService {

}
