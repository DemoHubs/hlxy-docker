package com.hlxy.sleuth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/23.</p>
 */
@SpringBootApplication
@EnableZipkinServer
//@EnableZipkinStreamServer
@EnableDiscoveryClient
public class SleuthApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SleuthApplication.class).web(true).run(args);
    }
}
