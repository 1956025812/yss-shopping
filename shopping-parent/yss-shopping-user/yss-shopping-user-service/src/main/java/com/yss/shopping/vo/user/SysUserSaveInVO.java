package com.yss.shopping.vo.user;

import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户新增InVO对象")
public class SysUserSaveInVO {

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
     * SysUserSaveInVO转换为SysUser
     *
     * @param sysUserSaveInVO
     * @return SysUser
     */
    public SysUser toSysUser(SysUserSaveInVO sysUserSaveInVO) {
        SysUser sysUser = new SysUser();
        if (null != sysUserSaveInVO) {
            sysUser.setUsername(sysUserSaveInVO.getUsername())
                    .setPassword(sysUserSaveInVO.getPassword())
                    .setNickname(sysUserSaveInVO.getNickname())
                    .setEmail(sysUserSaveInVO.getEmail())
                    .setHeadImgUrl(sysUserSaveInVO.getHeadImgUrl());
        }
        return sysUser;
    }



}
