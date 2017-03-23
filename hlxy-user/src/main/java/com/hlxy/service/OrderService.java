package com.hlxy.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/23.</p>
 */
@FeignClient(name="hlxy-order")
public interface OrderService {
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    String get(@RequestParam(name = "name") String name);
}
