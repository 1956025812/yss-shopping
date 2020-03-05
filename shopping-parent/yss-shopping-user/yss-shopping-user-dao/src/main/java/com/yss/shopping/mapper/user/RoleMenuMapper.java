package com.yss.shopping.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.shopping.dto.user.RoleMenuDTO;
import com.yss.shopping.entity.user.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {


    /**
     * 查询角色下菜单列表
     *
     * @param rid     角色ID
     * @param ridList 角色ID集合
     * @return 角色下菜单列表
     */
    List<RoleMenuDTO> selectMenuListOfRole(@Param("rid") Long rid, @Param("ridList") List<Long> ridList);
}
