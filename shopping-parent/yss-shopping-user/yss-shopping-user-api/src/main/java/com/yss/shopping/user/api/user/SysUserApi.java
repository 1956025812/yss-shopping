package com.yss.shopping.user.api.user;

import com.yss.shopping.common.vo.ResultVO;
import com.yss.shopping.user.api.vo.SysUserOutVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户的FEIGN服务
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@FeignClient(name = "YSS-SHOPPING-USER", decode404 = true)
public interface SysUserApi {

    /**
     * 根据用户ID查询用户
     *
     * @param uid 用户ID
     * @return SysUserOutVo
     */
    @GetMapping("/sysuser/select/by/id")
    ResultVO<SysUserOutVo> selectSysUserById(@RequestParam(value = "uid") Long uid);

}
