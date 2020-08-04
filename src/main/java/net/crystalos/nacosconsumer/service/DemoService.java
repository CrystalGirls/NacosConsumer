package net.crystalos.nacosconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

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
    private String demoServiceId = "NacosService";

    public String times() {
        //从注册中心获取服务地址信息
        ServiceInstance serviceInstance = loadBalancerClient.choose(demoServiceId);
        //生成全新的定位地址
        String url = String.format("http://%s:%s/nowtimes", serviceInstance.getHost(), serviceInstance.getPort());
        System.out.println("request url:" + url);
        return url;
    }
    public String hello(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(demoServiceId);
        String url = String.format("http://%s:%s/hello/"+name, serviceInstance.getHost(), serviceInstance.getPort());
        System.out.println("request url:" + url);
        return url;
    }
}
