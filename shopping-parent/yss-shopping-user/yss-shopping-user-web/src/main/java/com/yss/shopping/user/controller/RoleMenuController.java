package com.yss.shopping.user.controller;


import com.yss.shopping.common.controller.BaseController;
import com.yss.shopping.common.vo.ResultVO;
import com.yss.shopping.user.service.user.RoleMenuService;
import com.yss.shopping.user.vo.user.SysMenuSimpleOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色菜单CONTROLLER
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Api(tags = "角色菜单CONTROLLER")
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController {

    @Resource
    private RoleMenuService roleMenuService;


    @ApiOperation("查询角色下的菜单列表")
    @GetMapping("/list")
    public ResultVO selectMenuListOfRole(@ApiParam(value = "角色ID") @RequestParam Long rid) {
        List<SysMenuSimpleOutVO> roleMenuList = this.roleMenuService.selectMenuListOfRole(rid);
        return ResultVO.getSuccess("查询角色下面的菜单列表成功", roleMenuList);
    }


    @ApiOperation("查询父子角色下的菜单列表")
    @GetMapping("/parentAndChildren")
    public ResultVO selectParentAndChildRoleMenuList(@ApiParam(value = "角色ID") @RequestParam Long rid) {
        List<SysMenuSimpleOutVO> roleMenuList = this.roleMenuService.selectParentAndChildRoleMenuList(rid);
        return ResultVO.getSuccess("查询父子角色下的菜单列表成功", roleMenuList);
    }


    @ApiOperation("修改角色权限")
    @GetMapping("/update")
    public ResultVO updateRoleMenuRelation(@ApiParam(value = "角色ID") @RequestParam Long rid,
                                           @ApiParam(value = "菜单ID集合") @RequestParam List<Long> midList) {
        this.roleMenuService.updateRoleMenuRelation(rid, midList);
        return ResultVO.getSuccess("修改角色权限成功");
    }


}
