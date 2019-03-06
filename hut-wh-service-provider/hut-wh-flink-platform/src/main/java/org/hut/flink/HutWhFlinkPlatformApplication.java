package org.hut.flink;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HutWhFlinkPlatformApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(HutWhFlinkPlatformApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}

