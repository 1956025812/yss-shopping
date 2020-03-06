package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户分页VO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserPageVO extends BaseVO {

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态：0-删除，1-启用，2-禁用")
    private Integer state;

    @ApiModelProperty("注册来源：1-后台注册，2-用户注册，3-QQ，4-WX")
    private Integer registerSource;

}
