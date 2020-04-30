package com.yss.shopping.user.service.impl.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.common.constant.CommonConstant;
import com.yss.shopping.common.util.ListUtils;
import com.yss.shopping.user.constant.SysUserConstant;
import com.yss.shopping.user.dto.user.UserRoleDTO;
import com.yss.shopping.user.entity.user.SysRole;
import com.yss.shopping.user.entity.user.UserRole;
import com.yss.shopping.user.mapper.user.UserRoleMapper;
import com.yss.shopping.user.service.user.SysRoleService;
import com.yss.shopping.user.service.user.UserRoleService;
import com.yss.shopping.user.vo.user.SysRoleSimpleOutVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户角色SERVICE接口实现类
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private SysRoleService sysRoleService;


    @Override
    public List<Long> selectRidList(Long uid) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>(new UserRole().setUid(uid));
        List<UserRole> userRoleList = this.userRoleMapper.selectList(queryWrapper);
        return ListUtils.n(userRoleList).list(UserRole::getRid).to();
    }


    @Override
    public List<SysRoleSimpleOutVO> selectParentAndChildUserRoleList(Long loginUid, Long uid) {

        // 查询登录用户对应的角色列表
        List<SysRoleSimpleOutVO> parentSysRoleList = this.selectLoginUserRoleList(loginUid);
        // 查询指定用户的角色ID列表
        List<Long> uidList = this.selectRidList(uid);

        // 遍历登录用户的角色列表，如果指定用户有相同的角色，则追加相同标志
        ListUtils.n(parentSysRoleList).each(e -> {
            if (ListUtils.n(uidList).cv(e.getRid())) {
                e.setParentChildSameFlag(true);
            }
        });

        return parentSysRoleList;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserRoleRelation(Long uid, List<Long> ridList) {
        log.info("进入到updateUserRoleRelation方法，参数为：[uid= {}, ridList= {}]", uid, ridList);
        Assert.isTrue(null != uid || !CollectionUtils.isEmpty(ridList), "修改用户角色失败：参数异常");

        // 把用户旧的角色删除
        QueryWrapper<UserRole> delQueryWrapper = new QueryWrapper<>(new UserRole().setUid(uid));
        this.userRoleMapper.delete(delQueryWrapper);

        // 新增新的用户角色
        List<UserRole> newUserRoleList = ListUtils.n(ridList).list(eachRid ->
                new UserRole().setUid(uid).setRid(eachRid).setCreateInfo(CommonConstant.DEFAULT_SYSTEM_USER).setCreateTime(LocalDateTime.now())
        ).to();
        this.saveBatch(newUserRoleList);
    }


    /**
     * 查询登录用户的角色列表
     *
     * @param uid 登录用户UID
     */
    private List<SysRoleSimpleOutVO> selectLoginUserRoleList(Long uid) {

        // 如果是超管则获取所有的角色
        if (SysUserConstant.ADMIN_UID.equals(uid)) {
            log.info("当前登录的用户为超级管理员，需查出所有的角色");
            List<SysRole> sysRoleList = this.sysRoleService.selectAllRoleList();
            return ListUtils.n(sysRoleList).list(eachSysRole -> new SysRoleSimpleOutVO().toSysRoleSimpleOutVO(eachSysRole)).to();
        }

        // 查询普通用户的角色ID集合
        List<UserRoleDTO> userRoleDTOList = this.userRoleMapper.selectUserRoleList(uid);
        return ListUtils.n(userRoleDTOList).list(eachUserRoleDTO -> new SysRoleSimpleOutVO().toSysRoleSimpleOutVO(eachUserRoleDTO)).to();
    }
}
