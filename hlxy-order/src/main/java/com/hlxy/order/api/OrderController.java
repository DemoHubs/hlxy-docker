package com.hlxy.order.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/21.</p>
 */
@RestController
public class OrderController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    private ObjectMapper mapper=new ObjectMapper();
    private Map<String,Map<String,Object>> cache= Maps.newConcurrentMap();
    private AtomicInteger orderNo=new AtomicInteger(0);
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    @HystrixCommand
    public String add(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        if(cache.containsKey(name)){
            logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from cache");
            return getJSON(cache.get(name));
        }
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from insert");
        Map<String,Object> o=Maps.newConcurrentMap();
        o.put("订单发生时间",System.currentTimeMillis());
        o.put("订单发生人",name);
        o.put("订单号",orderNo.getAndIncrement());
        cache.put(name,o);
        return getJSON(cache.get(name));
    }
    @HystrixCommand
    @RequestMapping(value = "/get" ,method = RequestMethod.GET)
    public String get(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        if(cache.containsKey(name)){
            logger.info("/get, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from cache");
            return getJSON(cache.get(name));
        }
        logger.info("/get, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", not order ");
        return "not order";
    }
    private String getJSON(Object o){
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
}
