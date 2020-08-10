package com.yss.shopping.user.vo.user;

import lombok.Data;

/**
 * <p>
 * 权限VO对象
 * </p>
 *
 * @author DuXueBo
 * @since 2020-05-16 15:21
 */
@Data
public class PrivilegeMenuVO {

    private Long mid;
    private Integer menuType;
    private String menuCode;
    private Long parentMid;

    public PrivilegeMenuVO() {
    }

    public PrivilegeMenuVO(Long mid, Integer menuType, String menuCode, Long parentMid) {
        this.mid = mid;
        this.menuType = menuType;
        this.menuCode = menuCode;
        this.parentMid = parentMid;
    }
}
