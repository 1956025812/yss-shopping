package com.yss.shopping.user.dto.user;

import lombok.Data;

/**
 * <p>
 * 用户角色DTO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
public class UserRoleDTO {

    private Long urId;
    private Long uid;
    private Long rid;
    private String roleName;
    private Long parentRid;

}
