package com.smd.demo.mapper;

import com.smd.demo.model.User;

/**
 * 处理用户相关的数据库操作的映射类
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户名、密码查询用户是否存在，若存在，则可以登录
	 * 
	 * @param user
	 *            带有查询条件的user对象
	 * @return 数据库查询结果
	 */
	public User queryForLogin(User user);

	/**
	 * 根据用户名查询用户是否存在，若不存在，则可以注册或添加新用户
	 * 
	 * @param user
	 *            带有查询条件的user对象
	 * @return 数据库查询结果
	 */
	public User queryUserName(User user);
}
