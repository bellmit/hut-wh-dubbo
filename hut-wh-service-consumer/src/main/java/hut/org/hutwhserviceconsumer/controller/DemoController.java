package hut.org.hutwhserviceconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.hut.openapi.user.service.IDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hutwanghui on 2018/11/23.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@RestController
public class DemoController {
    @Reference(
            version = "1.0.0",
            group = "test",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private IDemoService demoService;

    @RequestMapping("/demo")
    public String sayHello(String message) {
        System.out.println("=============" + message);

        return demoService.helloDubbo(message);
    }

    @RequestMapping("/demo2")
    public String sayHello2() {
        System.out.println("=============");

        return demoService.helloDubbo2();
    }
}
