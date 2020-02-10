package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysMenuDetailOutVO;
import com.yss.shopping.vo.user.SysMenuOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "系统菜单CONTROLLER")
@RestController
@RequestMapping("/sysMenu")
@Slf4j
@Validated
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation("查询菜单列表")
    @GetMapping("/list")
    public ResultVO selectSysMenuList(
            @ApiParam(value = "菜单类型：1-菜单，2-按钮", example = "1")
            @RequestParam(required = false) Integer type,
            @ApiParam(value = "父菜单ID") @RequestParam(required = false) Long parmentId) {
        List<SysMenuOutVO> menuCodeAndNameList = this.sysMenuService.selectSysMenuList(type, parmentId);
        return ResultVO.getSuccess("查询菜单的代码和名称列表成功", menuCodeAndNameList);
    }


    @ApiOperation("查询菜单详情")
    @GetMapping("/detail")
    public ResultVO selectSysMenuDetail(@ApiParam(value = "菜单ID", required = true) @RequestParam Long mid) {
        SysMenuDetailOutVO sysMenuDetailOutVO = this.sysMenuService.selectSysMenuDetail(mid);
        return ResultVO.getSuccess("查询菜单详情成功", sysMenuDetailOutVO);
    }


}
