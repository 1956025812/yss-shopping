package com.yss.shopping.controller;

import com.yss.shopping.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * BaseController
 */
@ControllerAdvice
@RestController
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(value = Exception.class)
    public ResultVO globalExceptionHandle(Exception e) {
        logger.error("发生异常, 异常信息如下:", e);
        return ResultVO.getFailed(e.getMessage());
    }

}