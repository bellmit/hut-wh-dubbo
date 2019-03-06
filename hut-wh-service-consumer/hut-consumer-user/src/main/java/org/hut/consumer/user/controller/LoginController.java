package org.hut.consumer.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.hut.common.entity.R;
import org.hut.common.entity.SysUser;
import org.hut.common.entity.UserDTO;
import org.hut.common.utils.JWTUtil;
import org.hut.common.vo.UserVO;
import org.hut.openapi.user.service.IDemoService;
import org.hut.openapi.user.service.SysUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hutwanghui on 2019/1/2.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@RestController
@RequestMapping("/api/oauth")
@Log
public class LoginController {


    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserService sysUserService;


    @GetMapping("/require_auth")
    @RequiresAuthentication
    public R requireAuth() {
        return R.ok("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public R requireRole() {
        return R.ok("You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public R requirePermission() {
        return R.ok("You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R unauthorized() {
        return R.error("Unauthorized");
    }


    @RequestMapping("/ajaxLogin/default")
    public R<Boolean> ajaxLoginDefault(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
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

    @RequestMapping("/checkRegisteBoolean")
    public R<Boolean> checkRegisteBoolean(@RequestParam String username) {
        log.info(">>>>>>>>>>>>>>>>>校验用户【" + username + "】是否存在>>>>>>>>>>>>>>>>>>");
        if (sysUserService.findUserByUsername(username) != null) {
            return R.error("用户已存在！").put("flag", false);
        } else {
            return R.ok("用户名合法！").put("flag", true);
        }
    }

    @RequestMapping("/checkEmailBoolean")
    public R<Boolean> checkEmailBoolean(@RequestParam String email) {
        log.info(">>>>>>>>>>>>>>>>>校验邮箱【" + email + "】是否已经绑定>>>>>>>>>>>>>>>>>>");
        if (sysUserService.findUserByEmail(email) != null) {
            return R.error("用户已存在！").put("flag", false);
        } else {
            return R.ok("用户名合法！").put("flag", true);
        }
    }

    @RequestMapping("/checkPhoneBoolean")
    public R<Boolean> checkPhoneBoolean(@RequestParam String phone) {
        log.info(">>>>>>>>>>>>>>>>>校验手机【" + phone + "】是否已经绑定>>>>>>>>>>>>>>>>>>");
        if (sysUserService.findUserByMobile(phone) != null) {
            return R.error("手机已绑定！").put("flag", false);
        } else {
            return R.ok("手机未绑定！").put("flag", true);
        }
    }

    @RequestMapping("/registe")
    public R<Boolean> registe(@RequestBody SysUser sysUser) {
        System.out.println(">>>>>>>>>>>>>>>>>>用户注册信息："+sysUser.toString()+">>>>>>>>>>>>>>>>>");
        if (sysUserService.findUserByMobile(sysUser.getPhone()) == null && sysUserService.findUserByUsername(sysUser.getUsername())==null) {
            Boolean registFlag = sysUserService.regist(sysUser);
            return registFlag == true ? R.ok("注册成功！").put("flag", true) : R.error("注册失败！").put("flag", false);
        }
        return R.error("注册失败！").put("flag", false);
    }


    @RequestMapping("/ajaxLogin")
    public R<Boolean> ajaxLogin(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {

        log.info(">>>>>>>>>>>>>>>>>>用户【" + sysUser.getUsername() + "】尝试登陆>>>>>>>>>>>>>>>>>");
        UserVO loginUser = sysUserService.findUserByUsername(sysUser.getUsername());
        System.out.println("用户信息：" + loginUser.toString());
        try {
            if (loginUser.getPassword().equals(new Md5Hash(new Md5Hash(sysUser.getPassword(), sysUser.getUsername())).toString())) {
                log.info("密码加密后一致！！");
                request.getSession().setAttribute("user", sysUser);
                /**
                 * 将加了密的密码生成Token
                 */
                String token = JWTUtil.sign(sysUser.getUsername(), loginUser.getPassword());
                response.setHeader("Authorization", "Basic " + token);
                return R.ok("登陆成功！" + token);
            }
            return R.error("登陆失败！");
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


    @RequestMapping("/logout")
    public R<Boolean> logout() {
        return null;
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
