package com.yss.shopping.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.constant.user.SysUserConstant;
import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.mapper.user.SysUserMapper;
import com.yss.shopping.service.user.SysUserService;
import com.yss.shopping.util.FastJsonUtil;
import com.yss.shopping.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yss
 * @since 2019-12-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);


    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser selectUserById(String uid) {
        logger.info("根据用户ID查询用户信息，请求参数为：[uid:{}]", uid);
        SysUser sysUser = this.getById(uid);
        logger.info("查询到的用户信息为：{}", sysUser);
        return sysUser;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUser saveSysUser(SysUser sysUser) {
        logger.info("新增用户信息，接收过来的请求参数为：{}", FastJsonUtil.bean2Json(sysUser));
        sysUser.setPassword(Md5Util.toMD5(sysUser.getPassword()));
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setStatus(SysUserConstant.Status.OPEN.getKey());
        logger.info("新增用户，请求参数为：{}", FastJsonUtil.bean2Json(sysUser));
        boolean saveFlag = this.save(sysUser);
        Assert.isTrue(saveFlag, "新增用户失败");
        return sysUser;
    }
}
