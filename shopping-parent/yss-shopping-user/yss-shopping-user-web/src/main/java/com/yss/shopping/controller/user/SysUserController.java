package com.yss.shopping.controller.user;


import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysUserOutVo;
import com.yss.shopping.vo.user.SysUserSaveInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author yss
 * @since 2019-12-07
 */
@Api(tags = "系统用户CONTROLLER")
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("/select/by/id")
    public ResultVO<SysUserOutVo> selectSysUserById(@ApiParam(value = "用户ID", required = true) @RequestParam String uid) {
        logger.info("根基用户ID查询用户信息, 请求参数为：[uid:{}]", uid);
        SysUser sysUser = this.sysUserService.selectUserById(uid);
        SysUserOutVo sysUserOutVo = new SysUserOutVo().toSysUserOutVo(sysUser);
        return ResultVO.getSuccess("查询用户信息成功", sysUserOutVo);
    }


    @ApiOperation("新增用户对象")
    @PostMapping("/save")
    public ResultVO<Void> saveSysUser(@RequestBody SysUserSaveInVo sysUserSaveInVo) {
        logger.info("新增用户对象，请求参数为：{}", FastJsonUtil.bean2Json(sysUserSaveInVo));
        SysUser sysUser = this.sysUserService.saveSysUser(sysUserSaveInVo.toSysUser(sysUserSaveInVo));
        return ResultVO.getSuccess("新增用户成功");
    }


}
