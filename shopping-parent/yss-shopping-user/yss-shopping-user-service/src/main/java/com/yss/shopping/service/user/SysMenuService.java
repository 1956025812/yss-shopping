package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.vo.user.SysMenuDetailOutVO;
import com.yss.shopping.vo.user.SysMenuOutVO;
import com.yss.shopping.vo.user.SysMenuSaveInVO;
import com.yss.shopping.vo.user.SysMenuUpdateInVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 查询菜单列表
     *
     * @param type      菜单类型：1-菜单，2-按钮
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
     * @param sysMenuUpdateInVO
     */
    void updateSysMenu(SysMenuUpdateInVO sysMenuUpdateInVO);


    /**
     * 删除菜单对象
     *
     * @param mid 菜单ID
     */
    void delSysMenu(Long mid);
}
