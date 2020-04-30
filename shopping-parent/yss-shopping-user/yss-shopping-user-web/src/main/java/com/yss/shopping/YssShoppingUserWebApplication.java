package com.yss.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.yss.shopping", "com.yss.shopping.service"})
public class YssShoppingUserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingUserWebApplication.class, args);
    }

}