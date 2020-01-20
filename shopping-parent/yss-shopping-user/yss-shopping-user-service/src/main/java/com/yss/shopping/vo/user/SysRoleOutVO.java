package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("角色OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleOutVO {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("层级：从1开始")
    private Integer level;

    @ApiModelProperty("父角色ID，顶级为0")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;


}
