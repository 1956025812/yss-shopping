package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.volidation.IntegerEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("系统用户新增InVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserSaveInVO {

    @ApiModelProperty("注册来源：1-后台注册，2-用户注册，3-QQ，4-WX")
    @IntegerEnum(intValues = {1, 2, 3, 4}, message = "注册来源的值只能为1/2/3/4之一")
    private Integer registerSource;

    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号字段username不能为空")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱字段email不能为空")
    @Email(message = "邮箱格式不正确")
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
            sysUser.setRegisterSource(sysUserSaveInVO.getRegisterSource())
                    .setUsername(sysUserSaveInVO.getUsername())
                    .setNickname(sysUserSaveInVO.getNickname())
                    .setPassword(sysUserSaveInVO.getPassword())
                    .setEmail(sysUserSaveInVO.getEmail())
                    .setHeadImgUrl(sysUserSaveInVO.getHeadImgUrl());
        }
        return sysUser;
    }


}
