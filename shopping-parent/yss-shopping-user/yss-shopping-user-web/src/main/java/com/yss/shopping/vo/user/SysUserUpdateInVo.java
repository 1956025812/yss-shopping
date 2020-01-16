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

    @ApiModelProperty("头像路径")
    private String headImgUrl;


    /**
     * sysUserUpdateInVo转换为SysUser
     *
     * @param sysUserUpdateInVo
     * @return SysUser
     */
    public SysUser toSysUser(SysUserUpdateInVo sysUserUpdateInVo) {
        SysUser sysUser = new SysUser();
        if (null != sysUserUpdateInVo) {
            sysUser.setId(sysUserUpdateInVo.getUid())
                    .setPassword(sysUserUpdateInVo.getPassword())
                    .setNickname(sysUserUpdateInVo.getNickname())
                    .setEmail(sysUserUpdateInVo.getEmail())
                    .setHeadImgUrl(sysUserUpdateInVo.getHeadImgUrl());
        }
        return sysUser;
    }

}
