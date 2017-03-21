package com.hlxy.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/21.</p>
 */
@EnableEurekaServer
@SpringBootApplication
public class EureakaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EureakaServerApplication.class).web(true).run(args);
    }
}
