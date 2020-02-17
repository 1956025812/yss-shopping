package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.volidation.IntegerEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("系统菜单新增InVO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuSaveInVO implements Serializable {

    private static final long serialVersionUID = 888091386645680523L;

    @ApiModelProperty("菜单类型：1-页面，2-按钮")
    @NotNull(message = "菜单类型不能为空")
    @IntegerEnum(intValues = {1, 2}, message = "菜单类型的值只能为1/2")
    private Integer menuType;

    @ApiModelProperty("菜单代码")
    @NotBlank(message = "菜单代码不能为空")
    private String menuCode;

    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    @ApiModelProperty("菜单URL")
    private String menuUrl;

    @ApiModelProperty("父菜单ID，顶级为0")
    @NotNull(message = "父菜单不能为空")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;

    /**
     * SysMenuSaveInVO对象转换为SysMenu对象
     *
     * @param sysMenuSaveInVO
     * @return SysMenu
     */
    public SysMenu toSysMenu(SysMenuSaveInVO sysMenuSaveInVO) {
        SysMenu sysMenu = new SysMenu();
        if (null != sysMenuSaveInVO) {
            sysMenu.setMenuType(sysMenuSaveInVO.getMenuType()).setMenuCode(sysMenuSaveInVO.getMenuCode())
                    .setMenuName(sysMenuSaveInVO.getMenuName()).setMenuUrl(sysMenuSaveInVO.getMenuUrl())
                    .setParentId(sysMenuSaveInVO.getParentId()).setRemark(sysMenuSaveInVO.getRemark());
        }
        return sysMenu;
    }

}
