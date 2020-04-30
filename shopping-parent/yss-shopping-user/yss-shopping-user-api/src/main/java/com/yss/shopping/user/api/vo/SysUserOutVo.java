package com.yss.shopping.user.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * <p>
 * 用户OutVO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserOutVo {

    private Long uid;

    private String username;

    private String nickname;

    private String email;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
