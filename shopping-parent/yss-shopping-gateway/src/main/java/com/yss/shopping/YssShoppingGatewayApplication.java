package com.yss.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class YssShoppingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingGatewayApplication.class, args);
    }

}