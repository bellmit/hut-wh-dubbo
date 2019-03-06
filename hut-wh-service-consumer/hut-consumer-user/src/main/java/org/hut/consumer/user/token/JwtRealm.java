package org.hut.consumer.user.token;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hut.common.entity.SysUser;
import org.hut.common.utils.JWTUtil;
import org.hut.common.vo.SysRole;
import org.hut.common.vo.UserVO;
import org.hut.openapi.user.service.SysUserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hutwanghui on 2018/11/25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * 基于JWT（ JSON WEB TOKEN）的认证域
 * 实现校验jwt字符串是否合法
 */
@Log
public class JwtRealm extends AuthorizingRealm {

    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserService sysUserService;


    /**
     * 需要重写，不然会报错
     *
     * @return
     */
    @Override
    public Class<?> getAuthenticationTokenClass() {
        //此Realm只支持JwtToken
        return JwtToken.class;
    }

    /**
     * Jwt认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("%%%%%%%%Shiro框架进行用户jwt【" + token.getCredentials().toString() + "】的身份认证******");
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) token.getPrincipal();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(jwt);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        //通过username从数据库中查找 ManagerInfo对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserVO managerInfo = sysUserService.findUserByUsername(username);
        if (managerInfo == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JWTUtil.verify(jwt, username, managerInfo.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        //return new SimpleAuthenticationInfo(token, token, "my_realm");
        return new SimpleAuthenticationInfo(token, token, "jwtRealm");
    }


    /**
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        UserVO user = sysUserService.findUserByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> roleList = user.getRoleList();
        roleList.stream().forEach(r -> simpleAuthorizationInfo.addRole(r.getRoleName()));
//        Set<String> permission = new HashSet<>(Arrays.asList(
//                user.getRoleList().stream().forEach().split(",")));
//        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

}
