package org.hut.consumer.user.token;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by hutwanghui on 2018/11/25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * 基于JWT（ JSON WEB TOKEN）的认证域
 */
public class JwtRealm extends AuthorizingRealm {

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
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) token.getPrincipal();
        
        return null;
    }


    /**
     * Jwt授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }


}
