package org.hut.hutwhserviceconsumer.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by hutwanghui on 2018/11/25.
 * email:zjjhwanhui@163.com
 * qq:472860892
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
}
