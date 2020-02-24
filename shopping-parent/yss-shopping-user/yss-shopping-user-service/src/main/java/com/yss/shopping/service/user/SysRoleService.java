package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysRole;
import com.yss.shopping.vo.user.SysRoleDetailOutVO;
import com.yss.shopping.vo.user.SysRoleOutVO;
import com.yss.shopping.vo.user.SysRoleSaveInVO;
import com.yss.shopping.vo.user.SysRoleUpdateInVO;

import java.util.List;

/**
 * <p>
 * 系统角色服务类
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
     * @param rid  角色ID
     * @param roleState 角色状态
     */
    void updateSysRoleState(Long rid, Integer roleState);

}
