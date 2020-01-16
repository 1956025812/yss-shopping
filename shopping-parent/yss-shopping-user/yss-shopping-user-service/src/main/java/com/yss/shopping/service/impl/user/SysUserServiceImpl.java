package com.yss.shopping.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysUserConstant;
import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.mapper.user.SysUserMapper;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.util.ListUtils;
import com.yss.shopping.util.Md5Util;
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
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yss
 * @since 2019-12-07
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser selectUserById(Long uid) throws Exception {
        log.info("根据用户ID查询用户信息，请求参数为：[uid:{}]", uid);
        SysUser sysUser = this.getById(uid);
        log.info("查询到的用户信息为：{}", sysUser);
        return sysUser;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUser saveSysUser(SysUser sysUser) throws Exception {
        log.info("新增用户信息，接收过来的请求参数为：{}", FastJsonUtil.bean2Json(sysUser));

        // 账号和邮箱不能重复
        this.assertUsernameNotExist(sysUser.getUsername());
        this.assertEmailNotExist(sysUser.getEmail());

        // 新增用户
        sysUser.setPassword(Md5Util.toMD5(sysUser.getPassword()))
                .setCreateTime(LocalDateTime.now())
                .setState(SysUserConstant.State.OPEN.getKey());
        log.info("新增用户，请求参数为：{}", FastJsonUtil.bean2Json(sysUser));
        boolean saveFlag = this.save(sysUser);
        Assert.isTrue(saveFlag, "新增用户失败");

        return sysUser;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysUser(SysUser sysUser) throws Exception {
        log.info("修改用户信息，接收过来的请求参数为：{}", FastJsonUtil.bean2Json(sysUser));

        Long uid = sysUser.getId();

        // 用户ID对应的记录必须存在
        this.assertUidExist(uid);
        // 账号和邮箱不能重复
        this.assertUsernameNotExist(sysUser.getUsername());
        this.assertEmailNotExist(sysUser.getEmail());

        // 修改用户
        SysUser sysUserUpdate = new SysUser();
        sysUserUpdate.setId(uid);
        sysUserUpdate.setNickname(sysUser.getNickname());
        sysUserUpdate.setPassword(Md5Util.toMD5(sysUser.getPassword()));
        sysUserUpdate.setEmail(sysUser.getEmail());
        sysUserUpdate.setUpdateTime(LocalDateTime.now());
        log.info("修改用户，请求参数为：{}", FastJsonUtil.bean2Json(sysUserUpdate));
        boolean updateFlag = this.updateById(sysUserUpdate);
        Assert.isTrue(updateFlag, "修改用户失败");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysUserStatusBatch(Long[] uidList, Integer userStatus) throws Exception {
        log.info("批量修改用户状态, 请求参数为：[uidList:{}, userStatus:{}]", FastJsonUtil.bean2Json(uidList), userStatus);

        List<SysUser> sysUserList = ListUtils.n(uidList).list(eachUid -> {
            SysUser sysUser = new SysUser();
            sysUser.setId(eachUid);
            sysUser.setState(userStatus);
            return sysUser;
        }).to();

        if (!CollectionUtils.isEmpty(sysUserList)) {
            log.info("批量修改用户状态，请求参数为：[uidList:{}, userStatus:{}]", FastJsonUtil.bean2Json(uidList), userStatus);
            boolean updateFlag = this.updateBatchById(sysUserList);
            Assert.isTrue(updateFlag, "批量修改用户状态失败");
        }
    }


    /**
     * 断言账号不存在, 如果存在则抛出异常
     *
     * @param username 账号
     */
    private void assertUsernameNotExist(String username) {
        log.info("查询账号：{} 的用户数量", username);
        Assert.notNull(username, "username不能为空");
        Integer count = this.sysUserMapper.selectCount(new QueryWrapper<>(new SysUser().setUsername(username)));
        log.info("账号为：{} 的用户数量为：{}", username, count);
        Assert.isTrue(count == 0, "操作失败：账号已经存在，请重新输入");
    }


    /**
     * 断言邮箱不存在，存在则抛出异常
     */
    private void assertEmailNotExist(String email) {
        log.info("查询邮箱为：{} 的用户数量", email);
        Assert.notNull(email, "email不能为空");
        Integer count = this.sysUserMapper.selectCount(new QueryWrapper<>(new SysUser().setEmail(email)));
        log.info("邮箱为：{} 的用户数量为：{}", email, count);
        Assert.isTrue(count == 0, "操作失败：邮箱已经存在，请重新输入");
    }


    /**
     * 断言用户ID存在，不存在则抛出异常
     */
    private void assertUidExist(Long uid) {
        log.info("查询用户ID为：{} 的用户数量", uid);
        Assert.notNull(uid, "uid不能为空");
        Integer count = this.sysUserMapper.selectCount(new QueryWrapper<>(new SysUser().setId(uid)));
        log.info("用户ID为：{} 的用户数量为：{}", uid, count);
        Assert.isTrue(count > 0, "操作失败：用户不存在");
    }

}
