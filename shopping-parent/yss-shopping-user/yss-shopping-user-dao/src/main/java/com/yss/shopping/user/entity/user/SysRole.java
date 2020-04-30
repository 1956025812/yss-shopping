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
 * 角色对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 层级：从1开始
     */
    private Integer level;

    /**
     * 父角色ID，顶级为0
     */
    private Long parentId;

    /**
     * 状态：0-删除，1-启用，2-禁用
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建信息
     */
    private String createInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改信息
     */
    private String updateInfo;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
