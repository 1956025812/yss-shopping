package com.yss.shopping.user.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.user.entity.user.SysRole;
import com.yss.shopping.user.vo.user.SysRoleDetailOutVO;
import com.yss.shopping.user.vo.user.SysRoleOutVO;
import com.yss.shopping.user.vo.user.SysRoleSaveInVO;
import com.yss.shopping.user.vo.user.SysRoleUpdateInVO;

import java.util.List;

/**
 * <p>
 * 角色SERVICE接口实现类
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface SysRoleService extends IService<SysRole> {


    /**
     * 查询角色列表
     *
     * @param parentId 父角色ID
     * @return SysRoleOutVO集合
     */
    List<SysRoleOutVO> selectSysRoleList(Long parentId);


    /**
     * 查询角色详情
     *
     * @param rid 角色ID
     * @return SysRoleDetailOutVO
     */
    SysRoleDetailOutVO selectSysRoleDetail(Long rid);


    /**
     * 查询角色名称
     *
     * @param rid 角色ID
     * @return 角色名称
     */
    String selectRoleNameById(Long rid);


    /**
     * 新增角色信息
     *
     * @param sysRoleSaveInVO 新增角色VO对象
     * @return SysRoleOutVO
     */
    SysRoleOutVO saveSysRole(SysRoleSaveInVO sysRoleSaveInVO);


    /**
     * 修改角色信息
     *
     * @param sysRoleUpdateInVO 修改角色VO对象
     */
    void updateSysRole(SysRoleUpdateInVO sysRoleUpdateInVO);


    /**
     * 删除角色
     *
     * @param rid 角色ID
     */
    void delSysRole(Long rid);


    /**
     * 修改角色状态
     *
     * @param rid       角色ID
     * @param roleState 角色状态
     */
    void updateSysRoleState(Long rid, Integer roleState);


    /**
     * 查询指定角色的所有父角色ID集合
     *
     * @param rid             指定的角色ID
     * @param parentRoleState 父角色状态
     * @return 父角色ID集合
     */
    List<Long> selectParentRidList(Long rid, Integer parentRoleState);


    /**
     * 查询指定角色的所有子角色ID集合
     *
     * @param rid               指定的角色ID
     * @param childrenRoleState 子角色状态
     * @return 子角色ID集合
     */
    List<Long> selectChildrenRidList(Long rid, Integer childrenRoleState);


    /**
     * 查询角色对应的父角色ID
     *
     * @param rid 角色ID
     * @return 父角色ID
     */
    Long selectParentRid(Long rid);


    /**
     * 断言角色必须存在
     *
     * @param rid 角色ID
     */
    void assertRoleExist(Long rid);


    /**
     * 查询所有的角色列表
     *
     * @return 所有的未删除的角色列表
     */
    List<SysRole> selectAllRoleList();
}
