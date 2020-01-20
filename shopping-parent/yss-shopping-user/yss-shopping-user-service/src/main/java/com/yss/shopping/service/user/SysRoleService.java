package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysRole;
import com.yss.shopping.vo.user.SysRoleOutVO;
import com.yss.shopping.vo.user.SysRoleSaveInVO;

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
     * 新增角色信息
     *
     * @param sysRoleSaveInVO 新增角色VO对象
     * @return SysRoleOutVO
     */
    void saveSysRole(SysRoleSaveInVO sysRoleSaveInVO);
}
