package com.yss.shopping.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yss.shopping.dto.user.UserRoleDTO;
import com.yss.shopping.entity.user.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色Mapper 接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 查询用户的角色列表
     *
     * @param uid 用户ID
     * @return UserRoleDTO集合
     */
    List<UserRoleDTO> selectUserRoleList(@Param("uid") Long uid);

}
