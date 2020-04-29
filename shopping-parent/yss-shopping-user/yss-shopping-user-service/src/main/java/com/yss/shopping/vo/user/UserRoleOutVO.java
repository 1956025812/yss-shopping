package com.yss.shopping.vo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色VO对象
 * </p>
 *
 * @author DuXueBo
 * @since 2020-04-29 18:27
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
