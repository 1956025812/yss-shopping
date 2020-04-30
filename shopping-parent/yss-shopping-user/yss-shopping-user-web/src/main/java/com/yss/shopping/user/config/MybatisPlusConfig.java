package com.yss.shopping.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * mybatis-plus分页拦截插件
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Configuration
@MapperScan("com.yss.shopping.user.mapper.*")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
