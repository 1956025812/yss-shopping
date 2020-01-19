package com.yss.shopping.vo.user;

import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户修改InVO对象")
public class SysUserUpdateInVO {

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
     * sysUserUpdateInVO转换为SysUser
     *
     * @param sysUserUpdateInVO
     * @return SysUser
     */
    public SysUser toSysUser(SysUserUpdateInVO sysUserUpdateInVO) {
        SysUser sysUser = new SysUser();
        if (null != sysUserUpdateInVO) {
            sysUser.setId(sysUserUpdateInVO.getUid())
                    .setPassword(sysUserUpdateInVO.getPassword())
                    .setNickname(sysUserUpdateInVO.getNickname())
                    .setEmail(sysUserUpdateInVO.getEmail())
                    .setHeadImgUrl(sysUserUpdateInVO.getHeadImgUrl());
        }
        return sysUser;
    }


}
