package com.yss.shopping.user.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.user.entity.user.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单OutVO对象
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@ApiModel("菜单OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuOutVO {

    @ApiModelProperty("菜单ID")
    private Long mid;

    @ApiModelProperty("菜单类型：1-页面，2-按钮")
    private Integer menuType;

    @ApiModelProperty("菜单代码")
    private String menuCode;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单URL")
    private String menuUrl;

    @ApiModelProperty("父菜单ID，顶级为0")
    private Long parentId;

    @ApiModelProperty("层级：从1开始")
    private Integer level;

    @ApiModelProperty("状态：0-删除，1-启用，2-禁用")
    private Integer state;

    @ApiModelProperty("父菜单名称")
    private String parentMenuName;

    @ApiModelProperty("备注")
    private String remark;

    public SysMenuOutVO() {
    }


    /**
     * SysMenu对象转换为SysMenuOutVO对象
     *
     * @param sysMenu sysMenu
     * @return SysMenuOutVO
     */
    public SysMenuOutVO toSysMenuOutVO(SysMenu sysMenu, String parentMenuName) {
        SysMenuOutVO sysMenuOutVO = new SysMenuOutVO();
        if (null != sysMenu) {
            sysMenuOutVO.setMid(sysMenu.getId()).setMenuType(sysMenu.getMenuType())
                    .setMenuCode(sysMenu.getMenuCode()).setMenuName(sysMenu.getMenuName())
                    .setMenuUrl(sysMenu.getMenuUrl()).setParentId(sysMenu.getParentId())
                    .setLevel(sysMenu.getLevel()).setState(sysMenu.getState())
                    .setRemark(sysMenu.getRemark()).setParentMenuName(parentMenuName);
        }
        return sysMenuOutVO;
    }


}
