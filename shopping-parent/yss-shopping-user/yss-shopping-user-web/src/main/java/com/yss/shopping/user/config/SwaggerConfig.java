package com.yss.shopping.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * SWAGGER配置类
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yss.shopping.user.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("用户服务")
                        .description("用户服务相关接口")
                        .version("1.0")
//						.contact(new Contact("啊啊啊啊", "blog.csdn.net", "aaa@gmail.com"))
//						.license("The Apache License")
//						.licenseUrl("http://www.baidu.com")
                        .build());
    }
}
