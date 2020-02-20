package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("角色详情OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleDetailOutVO implements Serializable {

    private static final long serialVersionUID = -8976777615953235875L;

    @ApiModelProperty("角色ID")
    private Long rid;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("层级：从1开始")
    private Integer level;

    @ApiModelProperty("父角色ID，顶级为0")
    private Long parentId;

    @ApiModelProperty("角色状态：0-删除,1-启用，2-禁用")
    private Integer state;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("父角色名称")
    private String parentRoleName;


    /**
     * SysRole转换为SysRoleDetailOutVO
     *
     * @param sysRole
     * @return SysRoleDetailOutVO
     */
    public SysRoleDetailOutVO toSysRoleDetailOutVO(SysRole sysRole, String parentRoleName) {
        SysRoleDetailOutVO sysRoleOutVO = new SysRoleDetailOutVO();
        if (null != sysRole) {
            sysRoleOutVO.setRid(sysRole.getId()).setRoleName(sysRole.getRoleName())
                    .setLevel(sysRole.getLevel()).setParentId(sysRole.getParentId())
                    .setState(sysRole.getState()).setParentRoleName(parentRoleName)
                    .setRemark(sysRole.getRemark());
        }
        return sysRoleOutVO;
    }

}
