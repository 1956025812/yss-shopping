package com.yss.shopping.service.impl.user;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.CommonConstant;
import com.yss.shopping.constant.user.SysMenuConstant;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.mapper.user.SysMenuMapper;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuOutVO> selectSysMenuList(Integer type, Long parentId) {
        log.info("查询菜单的代码和名称列表，参数为：[type={}]", type);

        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq(null != type, SysMenuConstant.Column.MENU_TYPE.getKey(), type)
                .eq(null != parentId, SysMenuConstant.Column.PARENT_ID.getKey(), parentId)
                .eq(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.OPEN.getKey());
        List<SysMenu> sysMenuList = this.sysMenuMapper.selectList(sysMenuQueryWrapper);
        log.info("查询到的菜单数量为：{}", CollectionUtils.isEmpty(sysMenuList) ? 0 : sysMenuList.size());

        List<SysMenuOutVO> sysMenuOutVOList = ListUtils.n(sysMenuList).list(eachSysMenu -> {
            return new SysMenuOutVO().toSysMenuOutVO(eachSysMenu, null);
        }).to();

        return sysMenuOutVOList;
    }


    @Override
    public SysMenuDetailOutVO selectSysMenuDetail(Long mid) {
        log.info("根据菜单ID： {} 查询菜单信息", mid);

        SysMenu sysMenu = this.sysMenuMapper.selectById(mid);
        log.info("查询到的菜单为：{}", JSONUtil.toJsonStr(sysMenu));

        // 处理父级菜单名称
        String parentMenuName = this.selectMenuNameById(sysMenu.getParentId());

        SysMenuDetailOutVO sysMenuDetailOutVO = new SysMenuDetailOutVO().toSysMenuDetailOutVO(sysMenu, parentMenuName);

        return sysMenuDetailOutVO;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysMenuOutVO saveSysMenu(SysMenuSaveInVO sysMenuSaveInVO) {
        log.info("新增菜单信息，参数为：{}", JSONUtil.toJsonStr(sysMenuSaveInVO));

        SysMenu sysMenu = sysMenuSaveInVO.toSysMenu(sysMenuSaveInVO);
        Long parentId = sysMenu.getParentId();
        Integer menuType = sysMenu.getMenuType();

        // 常规校验: 菜单代码不能重复;父菜单ID必须存在;
        this.assertMenuCodeNotExist(sysMenu.getMenuCode());
        this.assertMenuIdExist(parentId);

        // 处理新增菜单类型与子菜单的关系
        this.assertMenuTypeAndChildRelation(menuType, parentId);

        // 处理菜单级别
        Integer nextMenuLevel = this.handleNextMenuLevel(parentId);

        // 新增菜单
        sysMenu.setLevel(nextMenuLevel).setState(SysMenuConstant.State.OPEN.getKey())
                .setCreateInfo(CommonConstant.DEFAULT_SYSTEM_USER).setCreateTime(LocalDateTime.now());
        log.info("新增菜单对象，参数为:{}", JSONUtil.toJsonStr(sysMenu));
        int saveCount = this.sysMenuMapper.insert(sysMenu);
        Assert.isTrue(saveCount == 1, "新增菜单失败");

        return new SysMenuOutVO().toSysMenuOutVO(sysMenu, null);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysMenu(SysMenuUpdateInVO sysMenuUpdateInVO) {
        log.info("修改菜单信息，参数为：{}", JSONUtil.toJsonStr(sysMenuUpdateInVO));

        Long mid = sysMenuUpdateInVO.getMid();

        // 断言菜单ID对应的记录必须存在
        this.assertMenuIdExist(mid);

        // 当修改的菜单类型为按钮时菜单URL不能为空 TODO

        // 修改菜单
        SysMenu updateSysMenu = new SysMenu();
        updateSysMenu.setId(mid).setMenuName(sysMenuUpdateInVO.getMenuName())
                .setMenuCode(sysMenuUpdateInVO.getMenuCode()).setRemark(sysMenuUpdateInVO.getRemark())
                .setUpdateInfo(CommonConstant.DEFAULT_SYSTEM_USER)
                .setUpdateTime(LocalDateTime.now());
        this.sysMenuMapper.updateById(updateSysMenu);
    }


    @Override
    public String selectMenuNameById(Long mid) {
        String menuName = null;
        if (null != mid) {
            SysMenu sysMenu = this.sysMenuMapper.selectById(mid);
            menuName = null != sysMenu ? sysMenu.getMenuName() : null;
        }
        return menuName;
    }


    @Override
    public void assertMenuCodeNotExist(String menuCode) {
        log.info("查询菜单代码为：{} 的菜单数量", menuCode);
        Assert.notNull(menuCode, "menuCode不能为空");
        QueryWrapper<SysMenu> menuCodeNotExistWarpper = new QueryWrapper<>(new SysMenu().setMenuCode(menuCode))
                .eq(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.OPEN.getKey());
        Integer count = this.sysMenuMapper.selectCount(menuCodeNotExistWarpper);
        log.info("菜单代码为：{} 的菜单数量为：{}", menuCode, count);
        Assert.isTrue(count == 0, "操作失败：菜单代码已经存在，请重新输入");
    }


    @Override
    public void assertMenuIdExist(Long mid) {
        log.info("查询菜单ID为：{} 的记录数量", mid);

        if (SysMenuConstant.PARENT_ID_DEFAULT_TOP.equals(mid)) {
            log.info("菜单ID：{} 为一级菜单，不需要检验", mid);
            return;
        }

        Assert.notNull(mid, "mid不能为空");
        Integer count = this.sysMenuMapper.selectCount(new QueryWrapper<>(new SysMenu().setId(mid)));
        log.info("菜单ID为：{} 的记录数量为：{}", mid, count);
        Assert.isTrue(count > 0, "操作失败: 菜单不存在");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delSysMenu(Long mid) {
        log.info("删除菜单，参数为：【mid={}】", mid);

        // 校验不能有子菜单
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>(new SysMenu().setParentId(mid))
                .eq(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.OPEN.getKey());
        Integer childrenCount = this.sysMenuMapper.selectCount(sysMenuQueryWrapper);
        log.info("查询菜单ID：{} 下的子菜单数量为：{}", childrenCount);
        Assert.isTrue(childrenCount == 0, "删除菜单失败：该菜单下有子菜单无法删除");

        // 删除菜单
        SysMenu delSysMenu = new SysMenu().setId(mid).setState(SysMenuConstant.State.DEL.getKey());
        int delCount = this.sysMenuMapper.updateById(delSysMenu);
        Assert.isTrue(delCount == 1, "删除菜单失败");
    }


    @Override
    public List<SysMenuSimpleOutVO> selectAllSysMenuList() {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.gt(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.DEL.getKey());
        List<SysMenu> sysMenuList = this.list(sysMenuQueryWrapper);
        log.info("查询到的菜单数量为：{}", ListUtils.n(sysMenuList).s());
        List<SysMenuSimpleOutVO> allSysMenuList = ListUtils.n(sysMenuList).list(eachSysMenu -> {
            return new SysMenuSimpleOutVO().toSysMenuSimpleOutVO(eachSysMenu);
        }).to();
        return allSysMenuList;
    }

    /**
     * 当新增的菜单类型为页面时，则父菜单类型必须是页面并且下面不能有按钮
     * 当新增的菜单类型为按钮时，则父菜单类型必须是页面并且下面不能有页面
     *
     * @param menuType 菜单类型
     * @param mid      菜单ID
     */
    private void assertMenuTypeAndChildRelation(Integer menuType, Long mid) {
        Assert.notNull(menuType, "menuType不能为空");
        Assert.notNull(mid, "mid不能为空");
        Assert.isTrue(null != SysMenuConstant.MenuType.containKey(menuType), "menuType的值必须为1或者2");

        Integer elseMenuType = SysMenuConstant.MenuType.PAGE.getKey().equals(menuType) ? SysMenuConstant.MenuType.BUTTON.getKey()
                : SysMenuConstant.MenuType.PAGE.getKey();
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>(new SysMenu().setParentId(mid).setMenuType(elseMenuType)
        ).eq(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.OPEN.getKey());
        Integer count = this.sysMenuMapper.selectCount(sysMenuQueryWrapper);
        log.info("查询出对应相反类型的子菜单的数量为：{}", count);
        String errorMsg = SysMenuConstant.MenuType.PAGE.getKey().equals(menuType) ?
                "新增菜单页面失败：当新增的菜单类型为页面时，则父菜单类型必须是页面并且下面不能有按钮"
                : "新增菜单按钮失败：当新增的菜单类型为按钮时，则父菜单类型必须是页面并且下面不能有页面";
        Assert.isTrue(count == 0, errorMsg);
    }


    /**
     * 处理菜单下级级别
     *
     * @param mid 菜单ID
     * @return 菜单的下级级别
     */
    private Integer handleNextMenuLevel(Long mid) {
        Assert.notNull(mid, "菜单ID不能为空");

        if (SysMenuConstant.PARENT_ID_DEFAULT_TOP.equals(mid)) {
            log.info("菜单ID：{} 为一级菜单，上级菜单为0", mid);
            return SysMenuConstant.LEVEL_DEFAULT_TOP;
        }

        SysMenu sysMenu = this.sysMenuMapper.selectById(mid);
        Assert.notNull(sysMenu, "操作失败：菜单不存在");
        return sysMenu.getLevel() + 1;
    }
}
