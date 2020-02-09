package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.vo.user.SysMenuOutVO;

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
     * @param type 菜单类型：1-菜单，2-按钮
     * @return SysMenuOutVO集合
     */
    List<SysMenuOutVO> selectSysMenuList(Integer type);
}
