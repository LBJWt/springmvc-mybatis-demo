package com.smd.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.demo.exception.UserException;
import com.smd.demo.mapper.UserMapper;
import com.smd.demo.model.Datagrid;
import com.smd.demo.model.User;
import com.smd.demo.util.Encrypt;

/**
 * 用户操作的业务层处理
 */
@Service("userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;// 处理用户相关的数据库操作mapper

	/*
	 * 方法次序：用户登录；用户管理（增删改查）；抛出异常示例（两种方式）
	 */

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            带有查询条件的User对象
	 * @return 查询结果
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public User login(User user) throws UserException {
		user.setPassword(Encrypt.md5(user.getPassword()));
		try {
			user = userMapper.queryForLogin(user);
		} catch (Exception e) {
			// 如果有异常，捕获后构造一个自定义异常，并抛出给controller处理
			throw new UserException("登录过程中，后台报错", e);
		}
		return user;
	}

	/**
	 * 检查用户名是否存在，用于注册和添加时校验用户名是否可用
	 * 
	 * @param user
	 *            带有查询条件的User对象
	 * @return 用户存在返回false，否则返回true
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public boolean checkUserName(User user) throws UserException {
		try {
			user = userMapper.queryUserName(user);
		} catch (Exception e) {
			throw new UserException("检查用户名时，后台报错", e);
		}
		if (user != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 新增用户，用于注册和添加用户
	 * 
	 * @param user
	 *            待插入数据库表的User对象
	 * @return 插入成功的User对象
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public User add(User user) throws UserException {
		user.setPassword(Encrypt.md5(user.getPassword()));
		Date currentDate = new Date();
		user.setCreateDate(currentDate);
		user.setModifyDate(currentDate);
		try {
			userMapper.save(user);
		} catch (Exception e) {
			throw new UserException("保存新用户时，后台报错", e);
		}
		return user;
	}

	/**
	 * 删除用户，可以删除一个或多个用户
	 * 
	 * @param user
	 *            带有查询条件的User对象
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public void remove(User user) throws UserException {
		try {
			if (user.getIds() == null || "".equals(user.getIds())) {// 如果ids为空，表示只删除一个
				userMapper.deleteById(user.getId());
			} else {
				userMapper.deleteList(user.getIds().split(","));
			}
		} catch (Exception e) {
			throw new UserException("删除用户时，后台报错", e);
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            带有查询条件的User对象
	 * @return 更新后的User对象
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public User edit(User user) throws UserException {
		user.setPassword(Encrypt.md5(user.getPassword()));
		Date currentDate = new Date();
		user.setModifyDate(currentDate);
		try {
			userMapper.update(user);
		} catch (Exception e) {
			throw new UserException("更新新用户时，后台报错", e);
		}
		return user;
	}

	/**
	 * 查询获取用户列表的datagrid对象
	 * 
	 * @param user
	 *            带有查询条件的User对象
	 * @return 查询结果组成的datagrid，供前台显示
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public Datagrid<User> listUser(User user) throws UserException {
		int total = 0;
		List<User> users = new ArrayList<User>();
		user.setStartRow();// 设置开始行，用于分页查询
		try {
			total = userMapper.selectCount(user);
			users = userMapper.selectList(user);
		} catch (Exception e) {
			throw new UserException("获取用户列表时，后台报错", e);
		}
		if (total != 0 && users != null) {
			// 将查询结果写入为一个datagrid对象，并返回给controller
			Datagrid<User> datagrid = new Datagrid<User>();
			datagrid.setTotal(total);
			datagrid.setRows(users);
			return datagrid;
		} else {
			return new Datagrid<User>();
		}
	}

	/**
	 * ajax异常，模拟ajax提交异常
	 * 
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public void ajaxException() throws UserException {
		throw new UserException("这是一个主动抛出的Ajax异常！");
	}

	/**
	 * 跳转异常，模拟刷新或跳转页面的表单提交异常
	 * 
	 * @throws UserException
	 *             自定义用户操作异常，有异常时抛出给controller处理
	 */
	public void forwardException() throws UserException {
		throw new UserException("这是一个主动抛出的跳转异常！");
	}
}
