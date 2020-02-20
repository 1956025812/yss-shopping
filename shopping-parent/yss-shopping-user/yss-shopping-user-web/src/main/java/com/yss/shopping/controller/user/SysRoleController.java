package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysRoleService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysRoleDetailOutVO;
import com.yss.shopping.vo.user.SysRoleOutVO;
import com.yss.shopping.vo.user.SysRoleSaveInVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 系统角色前端控制器
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Api(tags = "系统角色CONTROLLER")
@RestController
@RequestMapping("/sysRole")
@Slf4j
@Validated
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;


    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    public ResultVO selectSysRoleList() {
        List<SysRoleOutVO> sysRoleOutVOList = this.sysRoleService.selectSysRoleList();
        return ResultVO.getSuccess("查询角色列表成功", sysRoleOutVOList);
    }


    @ApiOperation("查询角色详情")
    @GetMapping("/detail")
    public ResultVO selectSysRoleDetail(@ApiParam(value = "角色ID", required = true) @RequestParam Long rid) {
        SysRoleDetailOutVO sysRoleDetailOutVO = this.sysRoleService.selectSysRoleDetail(rid);
        return ResultVO.getSuccess("查询角色详情成功", sysRoleDetailOutVO);
    }


    @ApiOperation("新增系统角色")
    @PostMapping("/save")
    public ResultVO saveSysRole(@ApiParam(value = "新增角色InVO对象", required = true) @RequestBody @Valid SysRoleSaveInVO sysRoleSaveInVO) {
        this.sysRoleService.saveSysRole(sysRoleSaveInVO);
        return ResultVO.getSuccess("新增角色成功");
    }


}
