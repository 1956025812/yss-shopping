package com.yss.shopping.user.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.user.entity.user.RoleMenu;
import com.yss.shopping.user.vo.user.PrivilegeMenuVO;
import com.yss.shopping.user.vo.user.SysMenuSimpleOutVO;

import java.util.List;

/**
 * <p>
 * 角色菜单SERVICE接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface RoleMenuService extends IService<RoleMenu> {


    /**
     * 查询角色下的菜单列表
     *
     * @param rid 角色ID
     * @return SysMenuSimpleOutVO集合
     */
    List<SysMenuSimpleOutVO> selectMenuListOfRole(Long rid);


    /**
     * 查询父子角色下的菜单列表
     *
     * @param rid 角色ID
     * @return SysMenuSimpleOutVO集合
     */
    List<SysMenuSimpleOutVO> selectParentAndChildRoleMenuList(Long rid);


    /**
     * 修改角色权限
     *
     * @param rid     角色ID
     * @param midList 菜单ID
     */
    void updateRoleMenuRelation(Long rid, List<Long> midList);


    /**
     * 删除角色菜单关联关系
     *
     * @param rid 角色ID
     */
    void deleteRoleMenu(Long rid);


    /**
     * 批量添加角色菜单关联关系
     *
     * @param rid     角色ID
     * @param midList 菜单ID集合
     */
    void saveRoleMenuBatch(Long rid, List<Long> midList);


    /**
     * 根据角色ID集合查询去重的菜单列表
     *
     * @param ridList 角色ID集合
     * @return PrivilegeMenuVO集合
     */
    List<PrivilegeMenuVO> selectMenuList(List<Long> ridList);
}
