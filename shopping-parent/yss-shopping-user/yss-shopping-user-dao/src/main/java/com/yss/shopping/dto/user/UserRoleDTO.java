package com.yss.shopping.dto.user;

import lombok.Data;

/**
 * <p>
 * 用户角色DTO对象
 * </p>
 *
 * @author DuXueBo
 * @since 2020-04-29 18:38
 */
@Data
public class UserRoleDTO {

    private Long urId;
    private Long uid;
    private Long rid;
    private String roleName;
    private Long parentRid;

}
