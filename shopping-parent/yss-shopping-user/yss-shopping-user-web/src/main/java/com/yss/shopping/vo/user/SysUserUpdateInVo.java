package com.yss.shopping.vo.user;

import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户修改InVo对象")
public class SysUserUpdateInVo {

    @ApiModelProperty("用户ID")
    private Long uid;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    /**
     * sysUserUpdateInVo转换为SysUser
     *
     * @param sysUserUpdateInVo
     * @return SysUser
     */
    public SysUser toSysUser(SysUserUpdateInVo sysUserUpdateInVo) {
        SysUser sysUser = new SysUser();
        if (null != sysUserUpdateInVo) {
            sysUser.setId(sysUserUpdateInVo.getUid());
            sysUser.setPassword(sysUserUpdateInVo.getPassword());
            sysUser.setNickname(sysUserUpdateInVo.getNickname());
            sysUser.setEmail(sysUserUpdateInVo.getEmail());
        }
        return sysUser;
    }

}
