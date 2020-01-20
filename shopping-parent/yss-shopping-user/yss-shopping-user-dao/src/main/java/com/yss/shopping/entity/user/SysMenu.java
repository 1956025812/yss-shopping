package com.yss.shopping.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单类型：1-页面，2-按钮
     */
    private Integer menuType;

    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID，顶级为0
     */
    private Long parentId;

    /**
     * 层级：从1开始
     */
    private Integer level;

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
