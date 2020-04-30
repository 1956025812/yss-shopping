package com.yss.shopping.controller.user;


import com.yss.shopping.controller.BaseController;
import com.yss.shopping.service.user.UserRoleService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysRoleSimpleOutVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色前端控制器
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Api(tags = "用户角色控制类")
@RestController
@RequestMapping("/userRole")
@Slf4j
public class UserRoleController extends BaseController {

    @Resource
    private UserRoleService userRoleService;

    @ApiOperation("查询父子用户下的角色列表")
    @GetMapping("/parentAndChildren")
    public ResultVO selectParentAndChildUserRoleList(@ApiParam(value = "登录用户ID") @RequestParam Long loginUid,
                                                     @ApiParam(value = "用户ID") @RequestParam Long uid) {
        List<SysRoleSimpleOutVO> userRoleList = this.userRoleService.selectParentAndChildUserRoleList(loginUid, uid);
        return ResultVO.getSuccess("查询父子用户下的角色列表成功", userRoleList);
    }


    @ApiOperation("修改用户角色")
    @GetMapping("/update")
    public ResultVO updateUserRoleRelation(@ApiParam(value = "用户ID") @RequestParam Long uid,
                                           @ApiParam(value = "角色ID集合") @RequestParam List<Long> ridList) {
        this.userRoleService.updateUserRoleRelation(uid, ridList);
        return ResultVO.getSuccess("修改用户角色成功");
    }


}
