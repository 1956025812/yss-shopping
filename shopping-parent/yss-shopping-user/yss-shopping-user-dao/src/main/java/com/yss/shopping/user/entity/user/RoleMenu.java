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
 * 角色菜单对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long rid;

    /**
     * 菜单ID
     */
    private Long mid;

    /**
     * 创建信息
     */
    private String createInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
