package com.yss.shopping.cors;
 
import org.springframework.stereotype.Component;
 
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
/**
 * @author qjwyss
 * @date 2020/01/21
 * @description 全局跨域过滤器
 */
@Component
public class CorsFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        chain.doFilter(req, res);
    }
 
    @Override
    public void init(FilterConfig filterConfig) {
    }
 
    @Override
    public void destroy() {
    }
 
}