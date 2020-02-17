package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("系统菜单修改InVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuUpdateInVO implements Serializable {

    private static final long serialVersionUID = 888091386645680523L;

    @ApiModelProperty("菜单ID")
    @NotNull(message = "菜单ID不能为空")
    private Long mid;

    @ApiModelProperty("菜单代码")
    private String menuCode;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单URL")
    private String menuUrl;

    @ApiModelProperty("备注")
    private String remark;

}
