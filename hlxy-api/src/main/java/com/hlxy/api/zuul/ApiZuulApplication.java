package com.hlxy.api.zuul;

import com.hlxy.api.zuul.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/21.</p>
 */
@EnableZuulProxy
@SpringCloudApplication
public class ApiZuulApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiZuulApplication.class).web(true).run(args);
    }
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
