package com.yss.shopping.aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.shopping.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     */
    @Pointcut("execution(public * com.yss.shopping.controller..*.*(..))")
    public void controllerMethod() {
    }


    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder requestLog = new StringBuilder();
        requestLog.append("请求信息：").append("URL = {").append(request.getRequestURI()).append("},\t")
                .append("请求方式 = {").append(request.getMethod()).append("},\t")
                .append("请求IP = {").append(request.getRemoteAddr()).append("},\t")
                .append("类方法 = {").append(joinPoint.getSignature().getDeclaringTypeName()).append(".")
                .append(joinPoint.getSignature().getName()).append("},\t");

        if (joinPoint.getArgs().length == 0) {
            requestLog.append("请求参数 = {} ");
        } else {
            requestLog.append("请求参数 = ").append(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(joinPoint.getArgs()[0]));
        }

        log.info(requestLog.toString());
    }


    @AfterReturning(returning = "resultVO", pointcut = "controllerMethod()")
    public void logResultVOInfo(ResultVO resultVO) throws Exception {
        log.info("请求结果：" + resultVO.getCode() + "\t" + resultVO.getMsg());
    }

}