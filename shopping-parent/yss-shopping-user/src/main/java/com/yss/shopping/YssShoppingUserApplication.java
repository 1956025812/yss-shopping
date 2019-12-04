package com.yss.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class YssShoppingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingUserApplication.class, args);
    }

}