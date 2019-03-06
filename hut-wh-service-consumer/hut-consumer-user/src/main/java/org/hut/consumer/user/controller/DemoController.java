package org.hut.consumer.user.controller;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.hut.common.entity.R;
import org.hut.common.entity.SysUser;
import org.hut.common.entity.UserDTO;
import org.hut.common.utils.JWTUtil;
import org.hut.common.vo.UserVO;
import org.hut.openapi.user.service.IDemoService;
import org.hut.openapi.user.service.SysUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hutwanghui on 2018/11/23.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */

@Log
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




