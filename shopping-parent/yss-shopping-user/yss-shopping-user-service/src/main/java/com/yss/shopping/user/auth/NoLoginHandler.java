package com.yss.shopping.user.auth;

import com.alibaba.fastjson.JSONObject;
import com.yss.shopping.common.constant.CommonConstant;
import com.yss.shopping.common.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 当未登录或者token失效访问接口时，自定义的返回结果
 * </p>
 *
 * @author yss
 * @since 2020-05-15 16:35
 */
@Component
public class NoLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding(CommonConstant.Encoding.UTF8.getKey());
        response.setContentType(CommonConstant.ContentType.APPLICATION_JSON.getKey());
        response.getWriter().println(JSONObject.toJSONString(ResultVO.getNoLogin()));
        response.getWriter().flush();
    }
}
