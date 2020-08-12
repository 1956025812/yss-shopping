package com.yss.shopping.user.auth;

import com.yss.shopping.user.service.user.SysUserService;
import com.yss.shopping.user.vo.user.PrivilegeUserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * <p>
 * SpringSecurity的配置
 * </p>
 *
 * @author yss
 * @since 2020-05-15 16:31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private NoPrivilegeHandler noPrivilegeHandler;
    @Resource
    private NoLoginHandler noLoginHandler;
    @Resource
    private SysUserService sysUserService;


    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET, "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**").permitAll()
                // 对登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register").permitAll()
                // 跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 测试时全部运行访问
                .antMatchers("/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JwtFilter 在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
        httpSecurity.addFilterBefore(this.jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling().accessDeniedHandler(this.noPrivilegeHandler).authenticationEntryPoint(this.noLoginHandler);

    }


    /**
     * 用于配置UserDetailsService及PasswordEncoder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    /**
     * 用于根据用户名获取用户信息和权限
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            PrivilegeUserVO privilegeUserVO = this.sysUserService.selectPrivilegeUserVO(username);
            if (null != privilegeUserVO) {
                return new PrivilegeUserDetails(privilegeUserVO);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }


    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
