package com.yss.shopping.user.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * druid监控控制台配置
 * http://localhost:port/druid
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Configuration
public class DruidConfig {

    /**
     * 主要实现web监控的配置处理
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        // 表示进行druid监控的配置处理操作
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,192.168.202.233");
        // 黑名单
        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否可以重置数据源
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/*");
        // 排除
        filterRegistrationBean.addInitParameter("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}