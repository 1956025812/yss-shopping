package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysRoleService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysRoleOutVO;
import com.yss.shopping.vo.user.SysRoleSaveInVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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


    @ApiOperation("新增系统角色")
    @PostMapping("/save")
    public ResultVO saveSysRole(@ApiParam(value = "新增角色InVO对象", required = true) @RequestBody @Valid SysRoleSaveInVO sysRoleSaveInVO) {
        this.sysRoleService.saveSysRole(sysRoleSaveInVO);
        return ResultVO.getSuccess("新增角色成功");
    }



}
