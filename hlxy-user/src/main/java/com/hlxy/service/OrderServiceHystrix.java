package com.hlxy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/23.</p>
 */
@Service
public class OrderServiceHystrix implements OrderService {
    @Autowired
    private OrderService orderService;
    @Override
    @HystrixCommand(fallbackMethod = "getfallback")
    public String get(String name) {
        return "{\"用户\":\""+name+"\",orders:["+orderService.get(name)+"]}";
    }
    public String getfallback(String name){
        Map<String,Object > f=Maps.newHashMap();
        f.put(name,"获取订单列表失败");
        try {
            return new ObjectMapper().writeValueAsString(f);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
