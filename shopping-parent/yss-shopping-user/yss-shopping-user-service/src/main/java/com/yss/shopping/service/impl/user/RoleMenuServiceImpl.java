package com.yss.shopping.service.impl.user;


import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.CommonConstant;
import com.yss.shopping.constant.user.SysRoleConstant;
import com.yss.shopping.dto.user.RoleMenuDTO;
import com.yss.shopping.entity.user.RoleMenu;
import com.yss.shopping.mapper.user.RoleMenuMapper;
import com.yss.shopping.service.user.RoleMenuService;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.service.user.SysRoleService;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.user.SysMenuSimpleOutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
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
    @Autowired
    private SysRoleService sysRoleService;


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

        // 处理普通角色的菜单列表
        List<RoleMenuDTO> roleMenuDTOList = this.roleMenuMapper.selectMenuListOfRole(rid, null);
        roleMenuList = ListUtils.n(roleMenuDTOList).list(eachRoleMenuDTO -> {
            return new SysMenuSimpleOutVO().toSysMenuSimpleOutVO(eachRoleMenuDTO);
        }).to();

        return roleMenuList;
    }


    @Override
    public List<SysMenuSimpleOutVO> selectParentAndChildRoleMenuList(Long rid) {

        // 查询角色对应的父角色ID
        Long parentRid = this.sysRoleService.selectParentRid(rid);
        Assert.notNull(parentRid, "操作失败：父角色不存在");

        // 分别查询父角色和子角色的菜单列表
        List<SysMenuSimpleOutVO> parentRoleMenuList = this.selectMenuListOfRole(parentRid);
        List<SysMenuSimpleOutVO> childRoleMenuList = this.selectMenuListOfRole(rid);

        // 遍历父角色的菜单列表，如果子角色有相同的菜单，则追加相同标志
        ListUtils.n(parentRoleMenuList).each(eachParentRoleMenu -> {
            if (ListUtils.n(childRoleMenuList).cv(eachParentRoleMenu)) {
                eachParentRoleMenu.setParentChildSameFlag(true);
            }
        });

        return parentRoleMenuList;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenuRelation(Long rid, List<Long> midList) {
        log.info("修改角色权限，参数为：[rid={}, midList={}]", rid, JSONUtil.toJsonStr(midList));

        // 断言角色必须存在
        this.sysRoleService.assertRoleExist(rid);

        // 删除旧的角色菜单关系
        this.deleteRoleMenu(rid);

        // 添加新的角色菜单关系
        this.saveRoleMenuBatch(rid, midList);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleMenu(Long rid) {
        log.info("删除角色rid: {} 的菜单关联关系", rid);
        Assert.notNull(rid, "删除失败：rid不能为空");
        QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>(new RoleMenu().setRid(rid));
        this.roleMenuMapper.delete(roleMenuQueryWrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRoleMenuBatch(Long rid, List<Long> midList) {
        log.info("批量添加角色菜单关联关系，参数为：[rid={}, midList={}]", rid, JSONUtil.toJsonStr(midList));
        Assert.isTrue(null != rid && !CollectionUtils.isEmpty(midList), "添加角色菜单关联关系失败：参数异常");

        List<RoleMenu> roleMenuList = ListUtils.n(midList).list(eachMid -> {
            return new RoleMenu().setRid(rid).setMid(eachMid)
                    .setCreateInfo(CommonConstant.DEFAULT_SYSTEM_USER).setCreateTime(LocalDateTime.now());
        }).to();

        this.saveBatch(roleMenuList);
    }
}
