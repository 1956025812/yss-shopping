package com.yss.shopping.common.controller;

import com.yss.shopping.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * BASE控制类
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@ControllerAdvice
@RestController
@Slf4j
public class BaseController {

    @ExceptionHandler(value = Exception.class)
    public ResultVO globalExceptionHandle(Exception e) {
        log.error("发生异常, 异常信息如下:", e);
        return ResultVO.getFailed(e.getMessage());
    }

}