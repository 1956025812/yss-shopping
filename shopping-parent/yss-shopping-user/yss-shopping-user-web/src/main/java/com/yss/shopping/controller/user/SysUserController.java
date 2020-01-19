package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.vo.user.SysUserOutVO;
import com.yss.shopping.vo.user.SysUserSaveInVO;
import com.yss.shopping.vo.user.SysUserUpdateInVO;
import com.yss.shopping.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("/select/by/id")
    public ResultVO selectSysUserOutVOById(@ApiParam(value = "用户ID", required = true, example = "1") @RequestParam Long uid) {
        SysUserOutVO sysUserOutVO = this.sysUserService.selectSysUserOutVOById(uid);
        return ResultVO.getSuccess("查询用户信息成功", sysUserOutVO);
    }


    @ApiOperation("新增用户对象")
    @PostMapping("/save")
    public ResultVO saveSysUser(@ApiParam(value = "新增用户InVO对象", required = true) @RequestBody SysUserSaveInVO sysUserSaveInVO) {
        SysUserOutVO sysUserOutVO = this.sysUserService.saveSysUser(sysUserSaveInVO.toSysUser(sysUserSaveInVO));
        return ResultVO.getSuccess("新增用户成功", sysUserOutVO);
    }


    @ApiOperation("修改用户对象")
    @PostMapping("/update")
    public ResultVO updateSysUser(@ApiParam(value = "修改用户InVO对象", required = true) @RequestBody SysUserUpdateInVO sysUserUpdateInVO) {
        this.sysUserService.updateSysUser(sysUserUpdateInVO);
        return ResultVO.getSuccess("修改用户成功");
    }


    @ApiOperation("批量修改用户状态")
    @PostMapping("/update/status/batch")
    public ResultVO updateSysUserStatusBatch(
            @ApiParam(value = "用户ID集合", required = true) @RequestParam Long[] uidList,
            @ApiParam(value = "用户状态: 0-删除，1-启用，2-禁用", required = true, example = "1") @RequestParam Integer userState) {
        this.sysUserService.updateSysUserStatusBatch(uidList, userState);
        return ResultVO.getSuccess("批量修改用户状态成功");
    }


}
