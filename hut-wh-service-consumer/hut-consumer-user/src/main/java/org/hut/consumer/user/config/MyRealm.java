package org.hut.consumer.user.config;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.java.Log;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.hut.common.vo.UserVO;
import org.hut.openapi.user.service.SysUserService;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * 自定义权限匹配和账号密码匹配
 */
@Log
public class MyRealm extends AuthorizingRealm {

    @Reference(
            version = "1.0.0",
            group = "sys",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private SysUserService sysUserService;

    /**
     * 用户权限部分
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /**
     * 身份认证部分
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //通过Token获取用户名
        String username = (String) token.getPrincipal();
        log.info("******Shiro框架进行用户【" + username + "】的身份认证******");
        //通过username从数据库中查找user
        //实际项目中可以在这里做缓存，如果不做shiro自己也是有时间间隔机制的
        UserVO user = sysUserService.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getDelFlag().equals("-1")) {
            //抛出账户冻结异常
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户
                user,
                //密码
                user.getPassword(),
                //salt = username
                ByteSource.Util.bytes(user.getSalt()),
                //realm name
                getName()
        );
        //此会将这个对象交给MyRealm进行加盐校验匹配
        return authenticationInfo;
    }
}
