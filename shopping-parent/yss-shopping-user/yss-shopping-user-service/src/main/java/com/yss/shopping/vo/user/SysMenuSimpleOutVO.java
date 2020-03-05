package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.dto.user.RoleMenuDTO;
import com.yss.shopping.entity.user.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("菜单简要OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuSimpleOutVO {

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
     * @return SysMenuSimpleOutVO
     */
    public SysMenuSimpleOutVO toSysMenuSimpleOutVO(SysMenu sysMenu) {
        SysMenuSimpleOutVO sysMenuSimpleOutVO = new SysMenuSimpleOutVO();
        if (null != sysMenu) {
            sysMenuSimpleOutVO.setMid(sysMenu.getId()).setMenuCode(sysMenu.getMenuCode())
                    .setMenuName(sysMenu.getMenuName()).setMenuType(sysMenu.getMenuType())
                    .setParentId(sysMenu.getParentId());
        }
        return sysMenuSimpleOutVO;
    }


    /**
     * RoleMenuDTO对象转换为RoleMenuDetailOutVO对象
     *
     * @param roleMenuDTO
     * @return SysMenuSimpleOutVO
     */
    public SysMenuSimpleOutVO toSysMenuSimpleOutVO(RoleMenuDTO roleMenuDTO) {
        SysMenuSimpleOutVO sysMenuSimpleOutVO = new SysMenuSimpleOutVO();
        if (null != roleMenuDTO) {
            sysMenuSimpleOutVO.setMid(roleMenuDTO.getMid()).setMenuCode(roleMenuDTO.getMenuCode())
                    .setMenuName(roleMenuDTO.getMenuName()).setMenuType(roleMenuDTO.getMenuType())
                    .setParentId(roleMenuDTO.getParentId());
        }
        return sysMenuSimpleOutVO;
    }


}
