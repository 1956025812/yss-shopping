package com.yss.shopping;

import com.yss.shopping.filter.RewritePathBuildGatewayFilterFactory;
import com.yss.shopping.predicate.PathNoApiRoutePredicateFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class YssShoppingGatewayApplication {

    @Bean
    public PathNoApiRoutePredicateFactory pathNoApiRoutePredicateFactory() {
        return new PathNoApiRoutePredicateFactory();
    }

    @Bean
    public RewritePathBuildGatewayFilterFactory rewritePathBuildGatewayFilterFactory() {
        return new RewritePathBuildGatewayFilterFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(YssShoppingGatewayApplication.class, args);
    }

}