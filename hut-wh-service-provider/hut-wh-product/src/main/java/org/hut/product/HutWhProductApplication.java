package org.hut.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.hut.product.mapper")
public class HutWhProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutWhProductApplication.class, args);
    }
}
