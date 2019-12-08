package com.yss.shopping.controller.user;


import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.user.SysUserOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class SysUserController {

	private final Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;


	@ApiOperation("根据用户ID查询用户信息")
	@GetMapping("/select/by/id")
	public ResultVO<SysUserOutVo> selectUserById(@ApiParam(value = "用户ID", required = true) @RequestParam String uid) {
		SysUser sysUser = this.sysUserService.getById(uid);
		SysUserOutVo sysUserOutVo = new SysUserOutVo().toSysUserOutVo(sysUser);
		return ResultVO.getSuccess("查询用户信息成功", sysUserOutVo);
	}


}
