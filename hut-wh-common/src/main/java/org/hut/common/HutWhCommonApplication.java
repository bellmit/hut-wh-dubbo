package org.hut.common;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by hutwanghui on 2018/11/24.
 * email:zjjhwanhui@163.com
 * qq:472860892
 */
@SpringBootApplication
public class HutWhCommonApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HutWhCommonApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
