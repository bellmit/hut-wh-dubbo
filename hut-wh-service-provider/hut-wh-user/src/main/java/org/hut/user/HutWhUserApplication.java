package org.hut.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("org.hut.user.mapper")
public class HutWhUserApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HutWhUserApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
