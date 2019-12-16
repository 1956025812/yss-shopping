package com.yss.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.yss.shopping.mapper.*")
@ComponentScan(basePackages = {"com.yss.shopping", "com.yss.shopping.service"})
public class YssShoppingGoodsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingGoodsWebApplication.class, args);
    }

}