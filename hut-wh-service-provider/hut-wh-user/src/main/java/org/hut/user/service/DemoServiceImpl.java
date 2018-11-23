package org.hut.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.hut.openapi.user.service.IDemoService;

/**
 * Created by hutwanghui on 2018/11/23.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "test"
)
public class DemoServiceImpl implements IDemoService {
    @Override
    public String helloDubbo(String message) {
        return "hello" + message;
    }

    @Override
    public String helloDubbo2() {
        return "hello";
    }
}
