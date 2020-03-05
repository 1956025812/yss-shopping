package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.RoleMenu;
import com.yss.shopping.vo.user.SysMenuSimpleOutVO;

import java.util.List;

/**
 * <p>
 * 服务类
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
     * @return 角色下的菜单列表
     */
    List<SysMenuSimpleOutVO> selectMenuListOfRole(Long rid);

}
