package com.yss.shopping.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 用户服务启动类
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.yss.shopping.user", "com.yss.shopping.common"})
public class YssShoppingUserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingUserWebApplication.class, args);
    }

}