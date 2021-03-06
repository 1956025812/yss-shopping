package com.yss.shopping.user.vo.user;

import com.yss.shopping.user.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统用户修改InVO对象
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@ApiModel("系统用户修改InVO对象")
public class SysUserUpdateInVO {

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID字段uid不能为空")
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
     * @param sysUserUpdateInVO sysUserUpdateInVO
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
