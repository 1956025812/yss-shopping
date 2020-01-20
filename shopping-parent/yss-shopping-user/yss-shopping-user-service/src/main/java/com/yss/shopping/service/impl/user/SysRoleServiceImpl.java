package com.yss.shopping.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.CommonConstant;
import com.yss.shopping.entity.user.SysRole;
import com.yss.shopping.mapper.user.SysRoleMapper;
import com.yss.shopping.service.user.SysRoleService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.vo.user.SysRoleSaveInVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSysRole(SysRoleSaveInVO sysRoleSaveInVO) {
        log.info("新增角色信息，接收过来的请求参数为：{}", FastJsonUtil.bean2Json(sysRoleSaveInVO));

        SysRole sysRole = sysRoleSaveInVO.toSysRole(sysRoleSaveInVO);

        // 角色名称不能重复
        this.assertRoleNameNotExist(sysRole.getRoleName());
        // 父角色ID必须存在
        this.assertParentRoleExist(sysRole.getParentId());

        sysRole.setCreateInfo(CommonConstant.DEFAULT_SYSTEM_USER)
                .setCreateTime(LocalDateTime.now());
        log.info("新增角色，请求参数为：{}", FastJsonUtil.bean2Json(sysRole));
        boolean saveFlag = this.save(sysRole);
        Assert.isTrue(saveFlag, "新增角色失败");
    }


    /**
     * 断言角色名称不存在
     */
    private void assertRoleNameNotExist(String roleName) {
        log.info("查询角色名称为：{} 的角色数量", roleName);
        Assert.notNull(roleName, "角色名称不能为空");
        Integer count = this.sysRoleMapper.selectCount(new QueryWrapper<>(new SysRole().setRoleName(roleName)));
        log.info("角色名称为：{} 的角色数量为：{}", roleName, count);
        Assert.isTrue(count == 0, "操作失败：角色名称已存在");
    }


    /**
     * 断言角色ID必须存在
     */
    private void assertParentRoleExist(Long roleId) {
        log.info("查询角色ID为：{} 的角色数量", roleId);
        Assert.notNull(roleId, "角色ID不能为空");
        Integer count = this.sysRoleMapper.selectCount(new QueryWrapper<>(new SysRole().setId(roleId)));
        log.info("角色ID为：{} 的角色数量为：{}", roleId, count);
        Assert.isTrue(count > 0, "角色ID对应的记录不存在");
    }


}