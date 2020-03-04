package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysMenuDetailOutVO;
import com.yss.shopping.vo.user.SysMenuOutVO;
import com.yss.shopping.vo.user.SysMenuSaveInVO;
import com.yss.shopping.vo.user.SysMenuUpdateInVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Api(tags = "菜单CONTROLLER")
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
            @ApiParam(value = "菜单类型：1-菜单，2-按钮") @RequestParam(required = false) Integer type,
            @ApiParam(value = "父菜单ID") @RequestParam(required = false) Long parentId) {
        List<SysMenuOutVO> menuCodeAndNameList = this.sysMenuService.selectSysMenuList(type, parentId);
        return ResultVO.getSuccess("查询菜单的代码和名称列表成功", menuCodeAndNameList);
    }


    @ApiOperation("查询菜单详情")
    @GetMapping("/detail")
    public ResultVO selectSysMenuDetail(@ApiParam(value = "菜单ID", required = true) @RequestParam Long mid) {
        SysMenuDetailOutVO sysMenuDetailOutVO = this.sysMenuService.selectSysMenuDetail(mid);
        return ResultVO.getSuccess("查询菜单详情成功", sysMenuDetailOutVO);
    }


    @ApiOperation("新增菜单对象")
    @PostMapping("/save")
    public ResultVO saveSysMenu(@ApiParam(value = "新增菜单InVO对象", required = true) @RequestBody
                                @Valid SysMenuSaveInVO sysMenuSaveInVO) {
        SysMenuOutVO sysMenuOutVO = this.sysMenuService.saveSysMenu(sysMenuSaveInVO);
        return ResultVO.getSuccess("新增菜单对象成功", sysMenuOutVO);
    }


    @ApiOperation("修改菜单对象")
    @PostMapping("/update")
    public ResultVO updateSysMenu(@ApiParam(value = "修改菜单InVO对象", required = true) @RequestBody
                                  @Valid SysMenuUpdateInVO sysMenuUpdateInVO) {
        this.sysMenuService.updateSysMenu(sysMenuUpdateInVO);
        return ResultVO.getSuccess("修改菜单对象成功");
    }


    @ApiOperation("删除菜单对象")
    @GetMapping("/del")
    public ResultVO delSysMenu(@ApiParam(value = "菜单ID", required = true) @RequestParam Long mid) {
        this.sysMenuService.delSysMenu(mid);
        return ResultVO.getSuccess("删除菜单详情成功");
    }


}
