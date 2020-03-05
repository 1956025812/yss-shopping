package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ApiModel("角色菜单OutVO对象")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleMenuOutVO {

    @ApiModelProperty("角色ID")
    private Long rid;

    @ApiModelProperty("角色下的菜单列表")
    private List<SysMenuSimpleOutVO> sysMenuSimpleOutVOList;

    public RoleMenuOutVO() {
    }

    public RoleMenuOutVO(Long rid, List<SysMenuSimpleOutVO> sysMenuSimpleOutVOList) {
        this.rid = rid;
        this.sysMenuSimpleOutVOList = sysMenuSimpleOutVOList;
    }
}
