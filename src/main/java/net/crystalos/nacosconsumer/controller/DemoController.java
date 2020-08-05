package net.crystalos.nacosconsumer.controller;

import net.crystalos.nacosconsumer.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 用例
 * Create on 2020/8/4 14:28
 *
 * @author Miss.Crystal
 * @version 1.0
 * Copyright (c) 2020 ♀Crystal♀ ,Inc. All Rights Reserved.
 */
@RestController
public class DemoController {

    @Resource(name = "demoService")
    private DemoService demoService;

    @RequestMapping(value = "/nowtimes", method = RequestMethod.GET)
    public String times() {
        return demoService.times();
    }
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name) {
        return demoService.hello(name);
    }
}
