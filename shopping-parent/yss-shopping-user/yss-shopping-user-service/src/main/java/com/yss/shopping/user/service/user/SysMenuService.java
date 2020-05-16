package com.yss.shopping.user.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.user.entity.user.SysMenu;
import com.yss.shopping.user.vo.user.*;

import java.util.List;

/**
 * <p>
 * 菜单SERVICE接口
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 查询菜单列表
     *
     * @param type     菜单类型：1-菜单，2-按钮
     * @param parentId 父菜单ID
     * @return SysMenuOutVO集合
     */
    List<SysMenuOutVO> selectSysMenuList(Integer type, Long parentId);


    /**
     * 查询菜单详情
     *
     * @param mid 菜单ID
     * @return SysMenuDetailOutVO
     */
    SysMenuDetailOutVO selectSysMenuDetail(Long mid);


    /**
     * 新增菜单对象
     *
     * @param sysMenuSaveInVO 菜单InVO对象
     * @return SysMenuOutVO
     */
    SysMenuOutVO saveSysMenu(SysMenuSaveInVO sysMenuSaveInVO);


    /**
     * 根据菜单ID查询菜单名称
     *
     * @param mid 菜单ID
     * @return 菜单名称
     */
    String selectMenuNameById(Long mid);


    /**
     * 断言菜单代码不存在, 否则抛异常
     *
     * @param menuCode 菜单代码
     */
    void assertMenuCodeNotExist(String menuCode);


    /**
     * 断言菜单ID必须存在, 否则抛异常
     *
     * @param mid 菜单ID
     */
    void assertMenuIdExist(Long mid);


    /**
     * 修改菜单对象
     *
     * @param sysMenuUpdateInVO 菜单修改VO对象
     */
    void updateSysMenu(SysMenuUpdateInVO sysMenuUpdateInVO);


    /**
     * 删除菜单对象
     *
     * @param mid 菜单ID
     */
    void delSysMenu(Long mid);


    /**
     * 查询所有的菜单集合
     *
     * @return 所有的菜单集合
     */
    List<SysMenuSimpleOutVO> selectAllSysMenuList();


    /**
     * 查询用户所有的权限列表
     *
     * @param uid 用户ID
     * @return 用户去重的权限列表
     */
    List<PrivilegeMenuVO> selectMenuList(Long uid);
}
