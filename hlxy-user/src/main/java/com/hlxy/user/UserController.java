package com.hlxy.user;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * <p>描述:</p>
 *
 * @author Tao xs
 * @since 2.0
 * <p>Created by Tao xs on 2017/3/21.</p>
 */
@RestController
public class UserController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    private Map<String,String> cache= Maps.newConcurrentMap();

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod="fff")
    public String add(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        if(cache.containsKey(name)){
            logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from cache");
            return String.format("cuurent user:%s ,id:%s",name,cache.get(name));
        }
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from insert");
        cache.put(name, Objects.toString(System.currentTimeMillis()));
        return String.format("cuurent user:%s ,id:%s",name,cache.get(name));
    }
    public String fff(String name){
        System.out.println(name);
        return "error";
    }
    @HystrixCommand
    @RequestMapping(value = "/get" ,method = RequestMethod.GET)
    public String get(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        if(cache.containsKey(name)){
            logger.info("/get, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", from cache");
            return String.format("cuurent user:%s ,id:%s",name,cache.get(name));
        }
        logger.info("/get, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", not user ");

        return String.format("not user :%s ",name);
    }
    @RequestMapping(value = "/update" ,method = RequestMethod.GET)
    public String update(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        if(cache.containsKey(name)){
            cache.put(name, Objects.toString(System.currentTimeMillis()));
            logger.info("/update, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + "");
            return String.format("cuurent user:%s ,id:%s",name,cache.get(name));
        }
        logger.info("/update, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", not user ");

        return String.format("not user :%s ",name);
    }
}
