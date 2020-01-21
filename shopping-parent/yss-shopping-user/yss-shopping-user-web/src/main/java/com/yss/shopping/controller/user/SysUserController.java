package com.yss.shopping.controller.user;


import com.yss.shopping.vo.PageVO;
import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysUserOutVO;
import com.yss.shopping.vo.user.SysUserPageVO;
import com.yss.shopping.vo.user.SysUserSaveInVO;
import com.yss.shopping.vo.user.SysUserUpdateInVO;
import com.yss.shopping.volidation.IntegerEnum;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Slf4j
@Validated
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询用户分页列表")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "currentPage", value = "当前页码", example = "1", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "每页记录数", example = "10", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用户账号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "nickname", value = "昵称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "email", value = "邮箱"),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "state", value = "状态：0-删除，1-启用，2-禁用", example = "1")
    })
    public ResultVO selectSysUserPage(@ApiIgnore SysUserPageVO sysUserPageVO) {
        PageVO<SysUserOutVO> sysUserOutVOPage = this.sysUserService.selectSysUserPage(sysUserPageVO);
        return ResultVO.getSuccess("查询用户分页列表成功", sysUserOutVOPage);
    }


    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("/select/by/id")
    public ResultVO selectSysUserOutVOById(@ApiParam(value = "用户ID", required = true, example = "1")
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


    @ApiOperation("批量修改用户状态")
    @PostMapping("/update/status/batch")
    public ResultVO updateSysUserStatusBatch(
            @ApiParam(value = "用户ID集合", required = true)
            @NotNull(message = "用户ID集合字段uidList不能为空") @RequestParam Long[] uidList,
            @ApiParam(value = "用户状态: 0-删除，1-启用，2-禁用", required = true, example = "1")
            @IntegerEnum(intValues = {0, 1, 2}, message = "用户状态的值只能为0/1/2") @RequestParam Integer userState) {
        this.sysUserService.updateSysUserStatusBatch(uidList, userState);
        return ResultVO.getSuccess("批量修改用户状态成功");
    }


    @ApiOperation("登录")
    @GetMapping("/denglu")
    public ResultVO denglu(
            @ApiParam(value = "账号", required = true) @NotEmpty(message = "用户账号username字段不能为空") @RequestParam String username,
            @ApiParam(value = "密码", required = true) @NotEmpty(message = "密码password字段不能为空") @RequestParam String password) throws Exception {
        SysUserOutVO sysUserOutVO = this.sysUserService.login(username, password);
        return ResultVO.getSuccess("登录成功", sysUserOutVO);
    }


}
