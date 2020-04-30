package com.yss.shopping.user.service.impl.user;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.common.util.ListUtils;
import com.yss.shopping.common.vo.PageVO;
import com.yss.shopping.user.constant.SysUserConstant;
import com.yss.shopping.user.entity.user.SysUser;
import com.yss.shopping.user.mapper.user.SysUserMapper;
import com.yss.shopping.user.service.user.SysUserService;
import com.yss.shopping.user.vo.user.SysUserOutVO;
import com.yss.shopping.user.vo.user.SysUserPageVO;
import com.yss.shopping.user.vo.user.SysUserSaveInVO;
import com.yss.shopping.user.vo.user.SysUserUpdateInVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户SERVICE接口实现类
 * </p>
 *
 * @author yss
 * @since 2020-01-20
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public PageVO<SysUserOutVO> selectSysUserPage(SysUserPageVO sysUserPageVO) {

        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.like(StringUtils.isNotEmpty(sysUserPageVO.getUsername()), SysUserConstant.Column.USERNAME.getKey(), sysUserPageVO.getUsername())
                .like(StringUtils.isNotEmpty(sysUserPageVO.getNickname()), SysUserConstant.Column.NICKNAME.getKey(), sysUserPageVO.getNickname())
                .like(StringUtils.isNotEmpty(sysUserPageVO.getEmail()), SysUserConstant.Column.EMAIL.getKey(), sysUserPageVO.getEmail())
                .eq(null != sysUserPageVO.getState(), SysUserConstant.Column.STATE.getKey(), sysUserPageVO.getState())
                .eq(null != sysUserPageVO.getRegisterSource(), SysUserConstant.Column.REGISTER_SOURCE.getKey(), sysUserPageVO.getRegisterSource())
                .gt(null != sysUserPageVO.getStartTime(), SysUserConstant.Column.CREATE_TIME.getKey(), sysUserPageVO.getStartTime())
                .lt(null != sysUserPageVO.getEndTime(), SysUserConstant.Column.CREATE_TIME.getKey(), sysUserPageVO.getEndTime())
                .gt(SysUserConstant.Column.STATE.getKey(), SysUserConstant.State.DEL.getKey())
                .orderByDesc(SysUserConstant.Column.CREATE_TIME.getKey());

        Page<SysUser> page = new Page<>(sysUserPageVO.getCurrentPage(), sysUserPageVO.getPageSize());
        Page<SysUser> sysUserPage = this.sysUserMapper.selectPage(page, sysUserQueryWrapper);
        List<SysUser> sysUserList = sysUserPage.getRecords();
        List<SysUserOutVO> sysUserOutVOList = ListUtils.n(sysUserList).list(eachSysUser -> new SysUserOutVO().toSysUserOutVO(eachSysUser)).to();


        return new PageVO<>(
                sysUserPage.getCurrent(), sysUserPage.getSize(), sysUserPage.getTotal(),
                sysUserPage.getPages(), sysUserOutVOList
        );
    }


    @Override
    public SysUserOutVO selectSysUserOutVOById(Long uid) {
        log.info("根据用户ID查询用户信息，请求参数为：[uid:{}]", uid);
        SysUser sysUser = this.getById(uid);
        log.info("查询到的用户信息为：{}", sysUser);
        return new SysUserOutVO().toSysUserOutVO(sysUser);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUserOutVO saveSysUser(SysUserSaveInVO sysUserSaveInVO) {
        log.info("新增用户信息，接收过来的请求参数为：{}", JSONUtil.toJsonStr(sysUserSaveInVO));

        SysUser sysUser = sysUserSaveInVO.toSysUser(sysUserSaveInVO);

        // 账号和邮箱不能重复
        this.assertUsernameNotExist(sysUser.getUsername());
        this.assertEmailNotExistExceptUser(sysUser.getEmail(), null);


        // 如果注册来源是后台注册，则默认密码
        String password = null;
        if (SysUserConstant.RegisterSource.SYSTEM.getKey().equals(sysUserSaveInVO.getRegisterSource())) {
            password = SysUserConstant.DEFAULT_PASSWORD;
        }

        // 新增用户
        sysUser.setPassword(SecureUtil.md5(password)).setCreateTime(LocalDateTime.now())
                .setState(SysUserConstant.State.OPEN.getKey());
        log.info("新增用户，请求参数为：{}", JSONUtil.toJsonStr(sysUser));
        boolean saveFlag = this.save(sysUser);
        Assert.isTrue(saveFlag, "新增用户失败");

        return new SysUserOutVO().toSysUserOutVO(sysUser);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysUser(SysUserUpdateInVO sysUserUpdateInVO) {
        log.info("修改用户信息，接收过来的请求参数为：{}", JSONUtil.toJsonStr(sysUserUpdateInVO));

        SysUser sysUser = sysUserUpdateInVO.toSysUser(sysUserUpdateInVO);
        Long uid = sysUser.getId();

        // 用户ID对应的记录必须存在
        this.assertUidExist(uid);
        // 邮箱不能重复
        this.assertEmailNotExistExceptUser(sysUser.getEmail(), uid);

        // 修改用户
        SysUser sysUserUpdate = new SysUser().setId(uid).setNickname(sysUser.getNickname()).setPassword(SecureUtil.md5(sysUser.getPassword()))
                .setEmail(sysUser.getEmail()).setUpdateTime(LocalDateTime.now());
        log.info("修改用户，请求参数为：{}", JSONUtil.toJsonStr(sysUserUpdate));
        boolean updateFlag = this.updateById(sysUserUpdate);
        Assert.isTrue(updateFlag, "修改用户失败");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSysUserStatus(Long uid, Integer userState) {
        log.info("修改用户状态, 请求参数为：[uid:{}, userStatus:{}]", uid, userState);
        Assert.notNull(uid, "修改用户状态，uid不能为空");
        SysUser sysUser = new SysUser().setId(uid).setState(userState).setUpdateTime(LocalDateTime.now());
        log.info("修改用户状态，参数为：{}", JSONObject.toJSONString(sysUser));
        int updateCount = this.sysUserMapper.updateById(sysUser);
        Assert.isTrue(updateCount == 1, String.format("修改用户uid: %s 的状态为：%s 失败", uid, userState));
    }


    @Override
    public void delSysUserBatch(Long[] uidList) {
        log.info("进入delSysUserBtach方法，参数为：[uidList= {}]", JSONObject.toJSONString(uidList));
        Assert.isTrue(null != uidList && uidList.length > 0, "批量删除好友失败，uidList不能为空");

        List<SysUser> sysUserList = new ArrayList<>(uidList.length);
        for (Long eachUid : uidList) {
            sysUserList.add(new SysUser().setId(eachUid).setState(SysUserConstant.State.DEL.getKey()).setUpdateTime(LocalDateTime.now()));
        }
        log.info("批量删除用户，参数为：{}", JSONObject.toJSONString(uidList));
        boolean flag = this.updateBatchById(sysUserList);
        Assert.isTrue(flag, "批量删除用户成功");
    }


    @Override
    public void resetPassword(Long uid) {
        log.info("进入resetPassword方法，参数为：[uid= {}]", uid);
        Assert.notNull(uid, "重置密码失败，uid不能为空");
        SysUser sysUser = new SysUser().setId(uid).setPassword(SysUserConstant.DEFAULT_PASSWORD).setUpdateTime(LocalDateTime.now());
        log.info("重置用户密码，参数为：{}", JSONObject.toJSONString(sysUser));
        int updateCount = this.sysUserMapper.updateById(sysUser);
        Assert.isTrue(updateCount == 1, String.format("重置用户uid: %s 的密码失败", uid));
    }


    @Override
    public SysUserOutVO login(String username, String password) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>(new SysUser().setUsername(username).setPassword(SecureUtil.md5(password)));
        SysUser sysUser = this.sysUserMapper.selectOne(sysUserQueryWrapper);
        Assert.notNull(sysUser, "登录失败，用户名或密码错误,请重新输入");
        return new SysUserOutVO().toSysUserOutVO(sysUser);
    }


    /**
     * 断言账号不存在, 如果存在则抛出异常
     *
     * @param username 账号
     */
    private void assertUsernameNotExist(String username) {
        log.info("查询账号：{} 的用户数量", username);
        Assert.notNull(username, "username不能为空");
        QueryWrapper<SysUser> emailNotExistWrapper = new QueryWrapper<>(new SysUser().setUsername(username))
                .gt(SysUserConstant.Column.STATE.getKey(), SysUserConstant.State.DEL.getKey());
        Integer count = this.sysUserMapper.selectCount(emailNotExistWrapper);
        log.info("账号为：{} 的用户数量为：{}", username, count);
        Assert.isTrue(count == 0, "操作失败：账号已经存在，请重新输入");
    }


    /**
     * 断言邮箱不存在[忽略指定用户的邮箱]，否则抛出异常
     */
    private void assertEmailNotExistExceptUser(String email, Long uid) {
        log.info("查询邮箱为：{} 的用户数量", email);
        Assert.notNull(email, "email不能为空");

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(new SysUser().setEmail(email))
                .ne(null != uid, SysUserConstant.Column.ID.getKey(), uid)
                .gt(SysUserConstant.Column.STATE.getKey(), SysUserConstant.State.DEL.getKey());

        Integer count = this.sysUserMapper.selectCount(queryWrapper);
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
