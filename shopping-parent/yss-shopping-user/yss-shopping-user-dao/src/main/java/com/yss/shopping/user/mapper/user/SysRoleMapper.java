package com.yss.shopping.user.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.shopping.user.entity.user.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色MAPPER接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {


    /**
     * 查询指定角色的所有父角色ID集合
     *
     * @param rid             角色ID
     * @param parentRoleState 父角色状态
     * @return 父角色ID集合
     */
    List<Long> selectParentRidList(@Param("rid") Long rid, @Param("parentRoleState") Integer parentRoleState);


    /**
     * 查询指定角色的所有子角色ID集合
     *
     * @param rid               指定的角色ID
     * @param childrenRoleState 子角色状态
     * @return 子角色ID集合
     */
    List<Long> selectChildrenRidList(@Param("rid") Long rid, @Param("childrenRoleState") Integer childrenRoleState);

}
