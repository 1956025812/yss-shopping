package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ApiModel("用户OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserOutVO {

    @ApiModelProperty("用户ID")
    private Long uid;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("头像路径")
    private String headImgUrl;

    @ApiModelProperty("用户状态：0-删除，1-启用，2-禁用")
    private Integer state;

    @ApiModelProperty("注册来源：1-后台注册，2-用户注册，3-QQ，4-WX")
    private Integer registerSource;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public SysUserOutVO() {
    }


    /**
     * SysUser对象转换为SysUserOutVO对象
     *
     * @param sysUser
     * @return SysUserOutVO
     */
    public SysUserOutVO toSysUserOutVO(SysUser sysUser) {
        SysUserOutVO sysUserOutVO = new SysUserOutVO();
        if (null != sysUser) {
            sysUserOutVO.setUid(sysUser.getId())
                    .setUsername(sysUser.getUsername())
                    .setNickname(sysUser.getNickname())
                    .setEmail(sysUser.getEmail())
                    .setHeadImgUrl(sysUser.getHeadImgUrl())
                    .setState(sysUser.getState())
                    .setRegisterSource(sysUser.getRegisterSource())
                    .setCreateTime(sysUser.getCreateTime());
        }
        return sysUserOutVO;
    }


}
