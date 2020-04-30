package com.yss.shopping.user.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.user.dto.user.UserRoleDTO;
import com.yss.shopping.user.entity.user.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色简要OutVO对象
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@ApiModel("角色简要OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleSimpleOutVO {

    @ApiModelProperty("角色ID")
    private Long rid;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("父角色ID，顶级为0")
    private Long parentId;

    @ApiModelProperty("父子角色有相同的角色时追加标志： 相同true, 不相同false")
    private Boolean parentChildSameFlag;


    /**
     * SysRole转换为SysRoleSimpleOutVO
     */
    public SysRoleSimpleOutVO toSysRoleSimpleOutVO(SysRole sysRole) {
        if (null == sysRole) {
            return null;
        }

        SysRoleSimpleOutVO sysRoleSimpleOutVO = new SysRoleSimpleOutVO();
        sysRoleSimpleOutVO.setRid(sysRole.getId()).setRoleName(sysRole.getRoleName())
                .setParentId(sysRole.getParentId());
        return sysRoleSimpleOutVO;
    }


    public SysRoleSimpleOutVO toSysRoleSimpleOutVO(UserRoleDTO userRoleDTO) {
        if(null == userRoleDTO) {
            return null;
        }

        SysRoleSimpleOutVO sysRoleSimpleOutVO = new SysRoleSimpleOutVO();
        sysRoleSimpleOutVO.setRid(userRoleDTO.getRid()).setRoleName(userRoleDTO.getRoleName());
        return sysRoleSimpleOutVO;
    }


}
