package com.yss.shopping.user.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.common.vo.PageVO;
import com.yss.shopping.user.entity.user.SysUser;
import com.yss.shopping.user.vo.user.*;

/**
 * <p>
 * 用户SERVICE接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 查询用户分页列表
     *
     * @param sysUserPageVO 用户分页VO对象
     * @return 用户分页列表
     */
    PageVO<SysUserOutVO> selectSysUserPage(SysUserPageVO sysUserPageVO);


    /**
     * 根据用户ID查询用户信息
     *
     * @param uid 用户ID
     * @return SysUserOutVO
     */
    SysUserOutVO selectSysUserOutVOById(Long uid);


    /**
     * 新增用户信息
     *
     * @param sysUserSaveInVO 新增用户VO对象
     * @return SysUserOutVO
     */
    SysUserOutVO saveSysUser(SysUserSaveInVO sysUserSaveInVO);


    /**
     * 修改用户信息
     *
     * @param sysUserUpdateInVO 修改用户VO对象
     */
    void updateSysUser(SysUserUpdateInVO sysUserUpdateInVO);


    /**
     * 修改用户状态
     *
     * @param uid       用户ID集合
     * @param userState 用户状态: 1-启用，2-禁用
     */
    void updateSysUserStatus(Long uid, Integer userState);


    /**
     * 批量删除用户
     *
     * @param uidList 用户ID集合
     */
    void delSysUserBatch(Long[] uidList);


    /**
     * 重置密码
     *
     * @param uid 用户ID
     */
    void resetPassword(Long uid);


    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return SysUserOutVO
     */
    SysUserOutVO login(String username, String password);


    /**
     * 根据用户账号查询用户权限VO对象
     *
     * @param username 账号
     * @return PrivilegeUserVO
     */
    PrivilegeUserVO selectPrivilegeUserVO(String username);


}
