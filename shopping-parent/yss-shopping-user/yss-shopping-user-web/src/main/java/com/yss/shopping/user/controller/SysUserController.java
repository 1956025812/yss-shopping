package com.yss.shopping.user.controller;


import com.yss.shopping.common.controller.BaseController;
import com.yss.shopping.common.vo.PageVO;
import com.yss.shopping.common.vo.ResultVO;
import com.yss.shopping.common.volidation.IntegerEnum;
import com.yss.shopping.user.service.user.SysUserService;
import com.yss.shopping.user.vo.user.SysUserOutVO;
import com.yss.shopping.user.vo.user.SysUserPageVO;
import com.yss.shopping.user.vo.user.SysUserSaveInVO;
import com.yss.shopping.user.vo.user.SysUserUpdateInVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户CONTROLLER
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Api(tags = "用户CONTROLLER")
@RestController
@RequestMapping("/sysuser")
@Slf4j
@Validated
public class SysUserController extends BaseController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private SysUserService sysUserService;


    @ApiOperation("查询用户分页列表")
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('userPage')")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "currentPage", value = "当前页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "每页记录数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用户账号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "nickname", value = "昵称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "email", value = "邮箱"),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "state", value = "状态：0-删除，1-启用，2-禁用"),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "registerSource", value = "注册来源：1-后台注册，2-用户注册，3-QQ，4-WX"),
    })
    public ResultVO selectSysUserPage(@ApiIgnore SysUserPageVO sysUserPageVO) {
        PageVO<SysUserOutVO> sysUserOutVOPage = this.sysUserService.selectSysUserPage(sysUserPageVO);
        return ResultVO.getSuccess("查询用户分页列表成功", sysUserOutVOPage);
    }


    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("/select/by/id")
    public ResultVO selectSysUserOutVOById(@ApiParam(value = "用户ID", required = true)
                                           @NotNull(message = "用户ID字段uid不能为空") @RequestParam Long uid) {
        SysUserOutVO sysUserOutVO = this.sysUserService.selectSysUserOutVOById(uid);
        return ResultVO.getSuccess("查询用户信息成功", sysUserOutVO);
    }


    @ApiOperation("新增用户对象")
    @PostMapping("/save")
    public ResultVO saveSysUser(@ApiParam(value = "新增用户InVO对象", required = true) @RequestBody @Valid SysUserSaveInVO sysUserSaveInVO) {
        SysUserOutVO sysUserOutVO = this.sysUserService.saveSysUser(sysUserSaveInVO);
        return ResultVO.getSuccess("新增用户成功", sysUserOutVO);
    }


    @ApiOperation("修改用户对象")
    @PostMapping("/update")
    public ResultVO updateSysUser(@ApiParam(value = "修改用户InVO对象", required = true) @RequestBody @Valid SysUserUpdateInVO sysUserUpdateInVO) {
        this.sysUserService.updateSysUser(sysUserUpdateInVO);
        return ResultVO.getSuccess("修改用户成功");
    }


    @ApiOperation("修改用户状态")
    @GetMapping("/update/status")
    public ResultVO updateSysUserStatus(
            @ApiParam(value = "用户ID", required = true) @NotNull(message = "用户ID字段uid不能为空") @RequestParam Long uid,
            @ApiParam(value = "用户状态: 1-启用，2-禁用", required = true)
            @IntegerEnum(intValues = {1, 2}, message = "用户状态的值只能为1/2") @RequestParam Integer userState) {
        this.sysUserService.updateSysUserStatus(uid, userState);
        return ResultVO.getSuccess("批量修改用户状态成功");
    }


    @ApiOperation("批量删除用户")
    @GetMapping("/del/batch")
    public ResultVO delSysUserBatch(@ApiParam(value = "用户ID集合", required = true) @NotNull(message = "用户ID集合不能为空") @RequestParam Long[] uidList) {
        this.sysUserService.delSysUserBatch(uidList);
        return ResultVO.getSuccess("批量删除用户成功");
    }


    @ApiOperation("重置密码")
    @GetMapping("/reset/password")
    public ResultVO resetPassword(@ApiParam(value = "用户ID", required = true) @NotNull(message = "用户ID字段uid不能为空") @RequestParam Long uid) {
        this.sysUserService.resetPassword(uid);
        return ResultVO.getSuccess("重置密码成功");
    }


    @ApiOperation("登录")
    @GetMapping("/denglu")
    public ResultVO denglu(
            @ApiParam(value = "账号", required = true) @NotEmpty(message = "用户账号username字段不能为空") @RequestParam String username,
            @ApiParam(value = "密码", required = true) @NotEmpty(message = "密码password字段不能为空") @RequestParam String password) {
        String token = this.sysUserService.login(username, password);
        if (null == token) {
            return ResultVO.getFailed("用户名或密码错误");
        }

        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ResultVO.getSuccess("登录成功", tokenMap);
    }


}
