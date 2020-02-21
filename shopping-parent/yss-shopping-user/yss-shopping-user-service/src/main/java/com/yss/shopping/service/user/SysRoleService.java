package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysRole;
import com.yss.shopping.vo.user.SysRoleDetailOutVO;
import com.yss.shopping.vo.user.SysRoleOutVO;
import com.yss.shopping.vo.user.SysRoleSaveInVO;

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
    void saveSysRole(SysRoleSaveInVO sysRoleSaveInVO);

}
