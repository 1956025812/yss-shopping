package com.yss.shopping.user.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 11550641637067612L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String headImgUrl;

    private Integer state;

    private Integer registerSource;

    private String createInfo;

    private LocalDateTime createTime;

    private String updateInfo;

    private LocalDateTime updateTime;


}
