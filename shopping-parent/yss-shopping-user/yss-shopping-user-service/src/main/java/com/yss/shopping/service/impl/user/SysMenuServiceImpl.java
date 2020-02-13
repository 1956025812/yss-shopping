package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.CommonConstant;
import com.yss.shopping.constant.user.SysMenuConstant;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.mapper.user.SysMenuMapper;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.user.SysMenuDetailOutVO;
import com.yss.shopping.vo.user.SysMenuOutVO;
import com.yss.shopping.vo.user.SysMenuSaveInVO;
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
    public List<SysMenuOutVO> selectSysMenuList(Integer type, Long parmentId) {
        log.info("查询菜单的代码和名称列表，参数为：[type={}]", type);

        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq(null != type, SysMenuConstant.Column.MENU_TYPE.getKey(), type)
                .eq(null != parmentId, SysMenuConstant.Column.PARENT_ID.getKey(), parmentId)
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
        log.info("查询到的菜单为：{}", FastJsonUtil.bean2Json(sysMenu));

        // 处理父级菜单名称
        String parentMenuName = this.selectMenuNameById(sysMenu.getParentId());

        SysMenuDetailOutVO sysMenuDetailOutVO = new SysMenuDetailOutVO().toSysMenuDetailOutVO(sysMenu, parentMenuName);

        return sysMenuDetailOutVO;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysMenuOutVO saveSysMenu(SysMenuSaveInVO sysMenuSaveInVO) {
        log.info("新增菜单信息，参数为：{}", FastJsonUtil.bean2Json(sysMenuSaveInVO));

        SysMenu sysMenu = sysMenuSaveInVO.toSysMenu(sysMenuSaveInVO);

        // 常规校验: 菜单代码不能重复;父菜单ID必须存在;
        this.assertMenuCodeNotExist(sysMenu.getMenuCode());
        this.assertMenuIdExist(sysMenu.getParentId());

        // 处理菜单级别
        Integer nextMenuLevel = this.handleNextMenuLevel(sysMenu.getParentId());

        // 新增菜单
        sysMenu.setLevel(nextMenuLevel).setState(SysMenuConstant.State.OPEN.getKey())
                .setCreateInfo(CommonConstant.DEFAULT_SYSTEM_USER).setCreateTime(LocalDateTime.now());
        log.info("新增菜单对象，参数为:{}", FastJsonUtil.bean2Json(sysMenu));
        int saveCount = this.sysMenuMapper.insert(sysMenu);
        Assert.isTrue(saveCount == 1, "新增菜单失败");

        return new SysMenuOutVO().toSysMenuOutVO(sysMenu, null);
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


    /**
     * 处理菜单下级级别
     *
     * @param mid 菜单ID
     * @return 菜单的下级级别
     */
    private Integer handleNextMenuLevel(Long mid) {
        Assert.notNull(mid, "菜单ID不能为空");
        SysMenu sysMenu = this.sysMenuMapper.selectById(mid);
        Assert.notNull(sysMenu, "操作失败：菜单不存在");
        return sysMenu.getLevel() + 1;
    }
}
