package com.hlxy.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/21.</p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminApplication.class).web(true).run(args);

    }
}
