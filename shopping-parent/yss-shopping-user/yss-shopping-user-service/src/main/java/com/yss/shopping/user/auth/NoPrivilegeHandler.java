package com.yss.shopping.user.auth;

import com.alibaba.fastjson.JSONObject;
import com.yss.shopping.common.constant.CommonConstant;
import com.yss.shopping.common.vo.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 当访问接口没有权限时，自定义的返回结果
 * </p>
 *
 * @author yss
 * @since 2020-05-15 16:49
 */
@Component
public class NoPrivilegeHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setCharacterEncoding(CommonConstant.Encoding.UTF8.getKey());
        response.setContentType(CommonConstant.ContentType.APPLICATION_JSON.getKey());
        response.getWriter().println(JSONObject.toJSONString(ResultVO.getNoPrivilege()));
        response.getWriter().flush();
    }
}
