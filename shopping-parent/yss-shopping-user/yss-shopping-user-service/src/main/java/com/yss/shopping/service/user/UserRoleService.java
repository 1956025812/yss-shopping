package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.UserRole;
import com.yss.shopping.vo.user.SysRoleSimpleOutVO;

import java.util.List;

/**
 * <p>
 * 用户角色SERVICE接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 查询用户下的角色ID集合
     *
     * @param uid 用户ID
     * @return 角色ID集合
     */
    List<Long> selectRidList(Long uid);


    /**
     * 查询父子用户下的角色列表
     *
     * @param loginUid 登录用户UID
     * @param uid      用户ID
     * @return SysRoleSimpleOutVO集合
     */
    List<SysRoleSimpleOutVO> selectParentAndChildUserRoleList(Long loginUid, Long uid);


    /**
     * 修改用户角色
     *
     * @param uid     用户ID
     * @param ridList 角色ID集合
     */
    void updateUserRoleRelation(Long uid, List<Long> ridList);
}
