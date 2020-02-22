package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("系统角色新增InVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleSaveInVO {

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称字段roleName不能为空")
    private String roleName;

    @ApiModelProperty("父角色ID，顶级为0")
    @NotNull(message = "父角色ID字段parentId不能为空")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;


    /**
     * SysRoleSaveInVO转换为SysRole
     *
     * @param sysRoleSaveInVO
     * @return SysRole
     */
    public SysRole toSysRole(SysRoleSaveInVO sysRoleSaveInVO) {
        SysRole sysRole = new SysRole();
        if (null != sysRoleSaveInVO) {
            sysRole.setRoleName(sysRoleSaveInVO.getRoleName())
                    .setParentId(sysRoleSaveInVO.getParentId())
                    .setRemark(sysRoleSaveInVO.getRemark());
        }
        return sysRole;
    }

}
