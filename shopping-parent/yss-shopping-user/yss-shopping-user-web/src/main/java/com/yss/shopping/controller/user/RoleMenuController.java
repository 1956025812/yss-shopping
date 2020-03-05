package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.RoleMenuService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysMenuSimpleOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Api(tags = "角色菜单CONTROLLER")
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController {

    @Autowired
    private RoleMenuService roleMenuService;


    @ApiOperation("查询角色下的菜单列表")
    @GetMapping("/list")
    public ResultVO selectMenuListOfRole(@ApiParam(value = "角色ID") @RequestParam Long rid) {
        List<SysMenuSimpleOutVO> roleMenuList = this.roleMenuService.selectMenuListOfRole(rid);
        return ResultVO.getSuccess("查询角色下面的菜单列表成功", roleMenuList);
    }


}
