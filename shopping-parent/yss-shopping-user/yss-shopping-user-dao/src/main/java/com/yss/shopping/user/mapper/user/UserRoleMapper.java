package com.yss.shopping.user.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.shopping.user.dto.user.UserRoleDTO;
import com.yss.shopping.user.entity.user.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色MAPPER接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 查询用户的角色列表
     *
     * @param uid 用户ID
     * @return UserRoleDTO集合
     */
    List<UserRoleDTO> selectUserRoleList(@Param("uid") Long uid);


    /**
     * 查询用户的角色ID集合
     *
     * @param uid       用户ID
     * @param roleState 角色状态：1-启用，2-禁用
     * @return 用户角色ID集合
     */
    List<Long> selectRidList(@Param("uid") Long uid, @Param("roleState") Integer roleState);
}
