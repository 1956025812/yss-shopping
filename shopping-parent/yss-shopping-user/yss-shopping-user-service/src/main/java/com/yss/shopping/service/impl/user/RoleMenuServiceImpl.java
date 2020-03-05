package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysRoleConstant;
import com.yss.shopping.dto.user.RoleMenuDTO;
import com.yss.shopping.entity.user.RoleMenu;
import com.yss.shopping.mapper.user.RoleMenuMapper;
import com.yss.shopping.service.user.RoleMenuService;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.ListUtils;
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
    public List<SysMenuSimpleOutVO> selectMenuListOfRole(Long rid) {
        log.info("查询角色对应的菜单列表，参数为：rid={}", rid);
        List<SysMenuSimpleOutVO> roleMenuList;

        // 如果角色包含天眼角色，则查询到所有的菜单
        if (SysRoleConstant.PARENT_ID_SUPER_MANAGER_TIYAN.equals(rid)) {
            log.info("当前角色为天眼角色，需查询出所有的菜单");
            roleMenuList = this.sysMenuService.selectAllSysMenuList();
            return roleMenuList;
        }

        // 处理普通角色的菜单列表 一个角色下的菜单ID是不会重复的 不需要去重
        List<RoleMenuDTO> roleMenuDTOList = this.roleMenuMapper.selectMenuListOfRole(rid, null);
        roleMenuList = ListUtils.n(roleMenuDTOList).list(eachRoleMenuDTO -> {
            return new SysMenuSimpleOutVO().toSysMenuSimpleOutVO(eachRoleMenuDTO);
        }).to();

        return roleMenuList;

    }


}
