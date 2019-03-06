package org.hut.consumer.user.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by hutwanghui on 2018/11/25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * 实现shiro校验载体的替换，只需要重新实现AuthenticationToken即可
 * 因为token自己已经包含了用户名等信息
 * 我们只需要在里面添加jwt字符串即可
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log
public class JwtToken implements AuthenticationToken {

    private String jwt;
    private String host;


    @Override
    public Object getPrincipal() {
        return this.getJwt();
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        String securtiy = new Md5Hash(new Md5Hash("password", "admin")).toString();
        System.out.println(securtiy);
    }
}
