package com.yss.shopping.user.vo.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 用户权限VO对象
 * </p>
 *
 * @author yss
 * @since 2020-05-16 15:04
 */
@Data
@Accessors(chain = true)
public class PrivilegeUserVO {

    private Long uid;
    private String username;
    private String password;
    private List<PrivilegeMenuVO> privilegeMenuVOList;

    public PrivilegeUserVO() {
    }


}

