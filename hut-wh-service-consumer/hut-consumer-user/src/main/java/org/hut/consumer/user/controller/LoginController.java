package org.hut.consumer.user.controller;

import lombok.extern.java.Log;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.hut.common.entity.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hutwanghui on 2019/1/2.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@RestController
@Log
public class LoginController {
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
}
