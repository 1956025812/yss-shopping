package com.yss.shopping.api.user;

import com.yss.shopping.api.vo.SysUserOutVo;
import com.yss.shopping.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户的FEIGN服务
 */
@FeignClient(name = "YSS-SHOPPING-USER", decode404 = true)
public interface SysUserApi {

    @GetMapping("/sysuser/select/by/id")
    ResultVO<SysUserOutVo> selectSysUserById(@RequestParam(value = "uid") Long uid);

}
