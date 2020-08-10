package com.yss.shopping.user.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.shopping.user.dto.user.RoleMenuDTO;
import com.yss.shopping.user.entity.user.RoleMenu;
import com.yss.shopping.user.entity.user.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单MAPPER接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
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
