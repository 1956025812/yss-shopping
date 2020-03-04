package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("角色菜单详情OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleMenuDetailOutVO {

    @ApiModelProperty("菜单ID")
    private Long mid;

    @ApiModelProperty("菜单代码")
    private String menuCode;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单类型：1-页面，2-按钮")
    private Integer menuType;

    @ApiModelProperty("父菜单ID，顶级为0")
    private Long parentId;

    /**
     * SysMenu对象转换为RoleMenuDetailOutVO对象
     *
     * @param sysMenu
     * @return RoleMenuDetailOutVO
     */
    public RoleMenuDetailOutVO toRoleMenuDetailOutVO(SysMenu sysMenu) {
        RoleMenuDetailOutVO roleMenuDetailOutVO = new RoleMenuDetailOutVO();
        if (null != sysMenu) {
            roleMenuDetailOutVO.setMid(sysMenu.getId()).setMenuCode(sysMenu.getMenuCode())
                    .setMenuName(sysMenu.getMenuName()).setMenuType(sysMenu.getMenuType())
                    .setParentId(sysMenu.getParentId());
        }
        return roleMenuDetailOutVO;
    }


}
