package com.yss.shopping.user.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色VO对象
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@ApiModel("用户角色VO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleOutVO implements Serializable {

    private Long uid;
    private Long rid;
    private String roleName;
    private Long parentRid;

}
