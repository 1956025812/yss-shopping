package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.common.vo.PageVO;
import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.vo.user.SysUserOutVO;
import com.yss.shopping.vo.user.SysUserPageVO;
import com.yss.shopping.vo.user.SysUserSaveInVO;
import com.yss.shopping.vo.user.SysUserUpdateInVO;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author yss
 * @since 2019-12-07
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 查询用户分页列表
     *
     * @param sysUserPageVO
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
     * @return 用户对象
     */
    void updateSysUser(SysUserUpdateInVO sysUserUpdateInVO);


    /**
     * 批量修改用户状态
     *
     * @param uidList   用户ID集合
     * @param userState 用户状态: 0-删除，1-启用，2-禁用
     */
    void updateSysUserStatusBatch(Long[] uidList, Integer userState);


}
