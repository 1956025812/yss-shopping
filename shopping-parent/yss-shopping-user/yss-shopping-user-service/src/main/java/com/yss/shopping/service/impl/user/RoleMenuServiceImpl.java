package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysMenuConstant;
import com.yss.shopping.constant.user.SysRoleConstant;
import com.yss.shopping.entity.user.RoleMenu;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.mapper.user.RoleMenuMapper;
import com.yss.shopping.service.user.RoleMenuService;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.user.RoleMenuDetailOutVO;
import com.yss.shopping.vo.user.RoleMenuOutVO;
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

        // 如果角色中包含天眼角色，则查询到所有的菜单
        if (rid.equals(SysRoleConstant.PARENT_ID_SUPER_MANAGER_TIYAN)) {
            roleMenuOutVO = this.handleTianYanRoleMenuList();
            return roleMenuOutVO;
        }

        // 处理普通角色的菜单


        return roleMenuOutVO;
    }


    @Override
    public RoleMenuOutVO selectMenuListOfRoles(List<Long> ridList) {
        return null;
    }

    /**
     * 处理天眼角色的所有菜单
     *
     * @return 所有的菜单
     */
    private RoleMenuOutVO handleTianYanRoleMenuList() {
        log.info("当前登录的用户是天眼角色，需要查询所有的菜单");
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.gt(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.DEL.getKey());
        List<SysMenu> sysMenuList = this.sysMenuService.list(sysMenuQueryWrapper);
        log.info("查询到的菜单数量为：{}", ListUtils.n(sysMenuList).s());
        List<RoleMenuDetailOutVO> roleMenuDetailOutVOList = ListUtils.n(sysMenuList).list(eachSysMenu -> {
            return new RoleMenuDetailOutVO().toRoleMenuDetailOutVO(eachSysMenu);
        }).to();
        return new RoleMenuOutVO(SysRoleConstant.PARENT_ID_SUPER_MANAGER_TIYAN, roleMenuDetailOutVOList);
    }

}
