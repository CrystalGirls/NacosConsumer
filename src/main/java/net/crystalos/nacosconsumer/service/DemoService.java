package net.crystalos.nacosconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Description: 用例
 * Create on 2020/8/4 14:29
 *
 * @author Miss.Crystal
 * @version 1.0
 * Copyright (c) 2020 ♀Crystal♀ ,Inc. All Rights Reserved.
 */
@Service("demoService")
public class DemoService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //用例的服务名称
    private final String demoServiceId = "NacosService";
    private final RestTemplate restTemplate = new RestTemplate();

    public String times() {
        //从注册中心获取服务地址信息
        ServiceInstance serviceInstance = loadBalancerClient.choose(demoServiceId);
        //生成服务地址，根据IP和端口拼接
        String url = String.format("http://%s:%s/nowtimes", serviceInstance.getHost(), serviceInstance.getPort());
        System.out.println("request url:" + url);
        //向服务端请求数据
        return restTemplate.getForObject(url, String.class);
    }
    public String hello(String name) {
        //从注册中心获取服务地址信息
        ServiceInstance serviceInstance = loadBalancerClient.choose(demoServiceId);
        //生成服务地址，直接获取URL
        String url = serviceInstance.getUri()+"/hello/"+name;
        System.out.println("request url:" + url);
        //向服务端请求数据
        return restTemplate.getForObject(url, String.class);
    }
}
