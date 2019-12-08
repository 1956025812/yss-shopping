package com.yss.shopping.service.user;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.entity.user.SysUser;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author yss
 * @since 2019-12-07
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 根据用户ID查询用户信息
	 *
	 * @param uid 用户ID
	 * @return
	 */
	SysUser selectUserById(String uid);

}
