package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysMenuConstant;
import com.yss.shopping.entity.user.SysMenu;
import com.yss.shopping.mapper.user.SysMenuMapper;
import com.yss.shopping.service.user.SysMenuService;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.vo.CodeAndNameVO;
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
    public List<SysMenuOutVO> selectSysMenuList(Integer type) {
        log.info("查询菜单的代码和名称列表，参数为：[type={}]", type);

        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq(null != type, SysMenuConstant.Column.MENU_TYPE.getKey(), type)
                .eq(SysMenuConstant.Column.STATE.getKey(), SysMenuConstant.State.OPEN.getKey());
        List<SysMenu> sysMenuList = this.sysMenuMapper.selectList(sysMenuQueryWrapper);
        log.info("查询到的菜单数量为：{}", CollectionUtils.isEmpty(sysMenuList) ? 0 : sysMenuList.size());

        List<SysMenuOutVO> sysMenuOutVOList = ListUtils.n(sysMenuList).list(eachSysMenu -> {
            return new SysMenuOutVO().toSysMenuOutVO(eachSysMenu);
        }).to();

        return sysMenuOutVOList;
    }


}
