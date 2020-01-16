package com.yss.shopping.vo.user;

import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户新增InVo对象")
public class SysUserSaveInVo {

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像路径")
    private String headImgUrl;

    /**
     * SysUserSaveInVo转换为SysUser
     *
     * @param sysUserSaveInVo
     * @return SysUser
     */
    public SysUser toSysUser(SysUserSaveInVo sysUserSaveInVo) {
        SysUser sysUser = new SysUser();
        if (null != sysUserSaveInVo) {
            sysUser.setUsername(sysUserSaveInVo.getUsername());
            sysUser.setPassword(sysUserSaveInVo.getPassword());
            sysUser.setNickname(sysUserSaveInVo.getNickname());
            sysUser.setEmail(sysUserSaveInVo.getEmail());
            sysUser.setHeadImgUrl(sysUserSaveInVo.getHeadImgUrl());
        }
        return sysUser;
    }

}
