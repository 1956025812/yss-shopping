package com.yss.shopping.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class TestNacosContreller {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Value("${age:0}")
    private Integer age;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping("/get/age")
    public Integer getAge() {
        return age;
    }

}
