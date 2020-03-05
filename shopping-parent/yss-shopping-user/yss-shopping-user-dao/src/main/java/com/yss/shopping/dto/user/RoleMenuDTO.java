package com.yss.shopping.dto.user;


import lombok.Data;

/**
 * 角色下菜单DTO对象
 */
@Data
public class RoleMenuDTO {

    private Long rmId;
    private Long rid;
    private Long mid;
    private String menuCode;
    private String menuName;
    private Integer menuType;
    private Long parentId;


}
