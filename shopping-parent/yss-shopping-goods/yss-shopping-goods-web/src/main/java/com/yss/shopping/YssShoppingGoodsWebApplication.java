package com.yss.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.yss.shopping.user.mapper.*")
@ComponentScan(basePackages = {"com.yss.shopping", "com.yss.shopping.user.service"})
@EnableFeignClients(basePackages = "com.yss.shopping.user.api.user")
public class YssShoppingGoodsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingGoodsWebApplication.class, args);
    }

}