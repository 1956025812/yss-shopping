package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("用户OutVo对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserOutVo {

	@ApiModelProperty("用户ID")
	private Long uid;

	@ApiModelProperty("用户账号")
	private String username;

	@ApiModelProperty("用户昵称")
	private String nickname;

	@ApiModelProperty("用户邮箱")
	private String email;

	@ApiModelProperty("用户状态：0-删除，1-启用，2-禁用")
	private Integer status;

	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public SysUserOutVo() {
	}

	/**
	 * SysUser对象转换为SysUserOutVo对象
	 *
	 * @param sysUser
	 * @return SysUserOutVo
	 */
	public SysUserOutVo toSysUserOutVo(SysUser sysUser) {
		SysUserOutVo sysUserOutVo = new SysUserOutVo();
		if (null != sysUser) {
			sysUserOutVo.setUid(sysUser.getId());
			sysUserOutVo.setUsername(sysUser.getUsername());
			sysUserOutVo.setNickname(sysUser.getNickname());
			sysUserOutVo.setEmail(sysUser.getEmail());
			sysUserOutVo.setStatus(sysUser.getStatus());
			sysUserOutVo.setCreateTime(sysUser.getCreateTime());
		}
		return sysUserOutVo;
	}

}
