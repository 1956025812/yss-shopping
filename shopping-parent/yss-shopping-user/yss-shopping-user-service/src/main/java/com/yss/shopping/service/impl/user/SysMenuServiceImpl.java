package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysMenuConstant;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.mapper.user.SysMenuMapper;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.user.SysMenuDetailOutVO;
import com.yss.shopping.vo.user.SysMenuOutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
            // 处理父菜单名称
            String parentMenuName = this.selectMenuNameById(eachSysMenu.getParentId());
            return new SysMenuOutVO().toSysMenuOutVO(eachSysMenu, parentMenuName);
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


    /**
     * 根据菜单ID查询菜单名称
     *
     * @param mid 菜单ID
     * @return 菜单名称
     */
    private String selectMenuNameById(Long mid) {
        String menuName = null;
        if (null != mid) {
            SysMenu sysMenu = this.sysMenuMapper.selectById(mid);
            menuName = null != sysMenu ? sysMenu.getMenuName() : null;
        }
        return menuName;
    }


}
