package com.yss.shopping.user.dto.user;


import lombok.Data;

/**
 * <p>
 * 角色下菜单DTO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
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
