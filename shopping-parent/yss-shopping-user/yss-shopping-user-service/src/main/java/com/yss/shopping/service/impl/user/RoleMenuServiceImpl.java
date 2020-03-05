package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysRoleConstant;
import com.yss.shopping.entity.user.RoleMenu;
import com.yss.shopping.mapper.user.RoleMenuMapper;
import com.yss.shopping.service.user.RoleMenuService;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.vo.user.RoleMenuOutVO;
import com.yss.shopping.vo.user.SysMenuSimpleOutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;


    @Override
    public RoleMenuOutVO selectMenuListOfRole(Long rid) {
        log.info("查询角色ID:{} 下对应的菜单列表", rid);

        RoleMenuOutVO roleMenuOutVO = null;

        // 如果角色是天眼角色，则查询到所有的菜单
        if (rid.equals(SysRoleConstant.PARENT_ID_SUPER_MANAGER_TIYAN)) {
            List<SysMenuSimpleOutVO> allSysMenuList = this.sysMenuService.selectAllSysMenuList();
            roleMenuOutVO = new RoleMenuOutVO(SysRoleConstant.PARENT_ID_SUPER_MANAGER_TIYAN, allSysMenuList);
            return roleMenuOutVO;
        }

        // 处理普通角色的菜单


        return roleMenuOutVO;
    }


    @Override
    public RoleMenuOutVO selectMenuListOfRoles(List<Long> ridList) {
        return null;
    }

}
