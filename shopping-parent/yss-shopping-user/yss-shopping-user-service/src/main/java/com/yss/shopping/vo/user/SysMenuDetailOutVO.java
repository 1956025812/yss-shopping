package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.entity.user.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ApiModel("菜单详情OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuDetailOutVO {

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

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建信息")
    private String createInfo;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("修改信息")
    private String updateInfo;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("父级菜单")
    private String parentMenuName;

    public SysMenuDetailOutVO() {
    }


    /**
     * SysMenu转换为SysMenuDetailOutVO
     *
     * @param sysMenu
     * @return SysMenuDetailOutVO
     */
    public SysMenuDetailOutVO toSysMenuDetailOutVO(SysMenu sysMenu, String parmentMenuName) {
        SysMenuDetailOutVO sysMenuDetailOutVO = new SysMenuDetailOutVO();
        if (null != sysMenu) {
            sysMenuDetailOutVO.setMid(sysMenu.getId()).setMenuType(sysMenu.getMenuType())
                    .setMenuCode(sysMenu.getMenuCode()).setMenuName(sysMenu.getMenuName())
                    .setMenuUrl(sysMenu.getMenuUrl()).setParentId(sysMenu.getParentId())
                    .setLevel(sysMenu.getLevel()).setState(sysMenu.getState())
                    .setRemark(sysMenu.getRemark()).setCreateInfo(sysMenu.getCreateInfo())
                    .setCreateTime(sysMenu.getCreateTime()).setUpdateInfo(sysMenu.getUpdateInfo())
                    .setUpdateTime(sysMenu.getUpdateTime()).setParentMenuName(parmentMenuName);
        }
        return sysMenuDetailOutVO;
    }

}
