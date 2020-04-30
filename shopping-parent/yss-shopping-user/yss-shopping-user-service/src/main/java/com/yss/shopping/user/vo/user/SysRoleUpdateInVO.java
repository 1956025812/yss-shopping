package com.yss.shopping.user.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.user.entity.user.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统角色修改InVO对象
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@ApiModel("系统角色修改InVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleUpdateInVO implements Serializable {

    private static final long serialVersionUID = -7440044255673218414L;

    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long rid;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("备注")
    private String remark;

    /**
     * SysRoleUpdateInVO转换为SysRole
     *
     * @param sysRoleUpdateInVO sysRoleUpdateInVO
     * @return SysRole
     */
    public SysRole toSysRole(SysRoleUpdateInVO sysRoleUpdateInVO) {
        SysRole sysRole = new SysRole();
        if (null != sysRoleUpdateInVO) {
            sysRole.setId(sysRoleUpdateInVO.getRid())
                    .setRoleName(sysRoleUpdateInVO.getRoleName())
                    .setRemark(sysRoleUpdateInVO.getRemark());
        }
        return sysRole;
    }

}
