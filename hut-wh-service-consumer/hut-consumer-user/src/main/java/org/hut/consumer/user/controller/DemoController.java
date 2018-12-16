package org.hut.consumer.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.hut.common.entity.R;
import org.hut.common.entity.SysUser;
import org.hut.common.entity.UserDTO;
import org.hut.openapi.user.service.IDemoService;
import org.hut.openapi.user.service.SysUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Reference(
            version = "1.0.0",
            group = "test",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserService sysUserService;


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


    @RequestMapping("/ajaxLogin")
    public R<Boolean> ajaxLogin(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        try {
            subject.login(usernamePasswordToken);
            request.getSession().setAttribute("user", sysUser);
            return new R<>(Boolean.TRUE, "登录成功");
        } catch (UnknownAccountException e) {
            throw new RuntimeException("账号不存在！");
        } catch (DisabledAccountException e) {
            throw new RuntimeException("账号未启用！");
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("密码错误！", e);
        } catch (ExcessiveAttemptsException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Throwable e) {
            throw new RuntimeException("发生未知错误，请联系管理员");
        }
    }

    @RequestMapping("/updateUserInfo")
    public R<Boolean> updateUserInfo(UserDTO userDto, String username) {
        if (StrUtil.isNotBlank(userDto.getPassword())
                && StrUtil.isNotBlank(userDto.getNewpassword1())) {
            String oldPasswd = new Md5Hash(new Md5Hash(userDto.getPassword(), userDto.getUsername())).toString();
            if (sysUserService.updateUserInfo(userDto, oldPasswd, String.valueOf(new Md5Hash(new Md5Hash(userDto.getNewpassword1(), userDto.getUsername())))) != null) {
                return new R<>(Boolean.TRUE, "修改密码成功");
            } else {
                log.info("原密码错误，修改密码失败:" + username);
                return new R<>(Boolean.FALSE, "原密码错误，修改失败");
            }
        }
        return new R<>(Boolean.FALSE, "原密码为空");
    }
}




