package com.yss.shopping.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.entity.user.SysUser;
import com.yss.shopping.mapper.user.SysUserMapper;
import com.yss.shopping.service.user.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
