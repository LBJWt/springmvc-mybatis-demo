package com.smd.demo.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smd.demo.exception.UserException;
import com.smd.demo.model.Datagrid;
import com.smd.demo.model.User;
import com.smd.demo.service.UserService;

/**
 * 处理用户相关界面的操作，从页面接收请求，向页面发送响应
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;// 处理用户操作的service对象

	/*
	 * 方法次序：注册登录相关；用户管理相关（增删改查）；异常抛出示例（两种方式）
	 */

	/**
	 * 登录
	 * 
	 * @param user
	 *            用户登录信息
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(User user, HttpServletRequest req, HttpServletResponse res) {
		try {
			user = userService.login(user);
		} catch (UserException e) {// 如果有异常，在前台显示异常消息
			sendFailureMessage(res, e.getMessage());
		}
		if (user != null) {
			req.getSession().setAttribute("user", user);
			sendSuccessJson(res, user, "登录成功！");
		} else {
			sendFailureMessage(res, "用户名或密码错误！");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param req
	 * @param res
	 * @return 返回登录页面
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().setAttribute("user", null);
		return toLogin();
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @return 注册页面
	 */
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET)
	public ModelAndView toRegister() {
		return forward("user/register", null);
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return 登录页面
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public ModelAndView toLogin() {
		return forward("user/login", null);
	}

	/**
	 * 注册
	 * 
	 * @param user
	 *            用户注册信息
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(User user, HttpServletRequest req, HttpServletResponse res) {
		try {
			user = userService.add(user);
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
		if (user != null) {
			req.getSession().setAttribute("user", user);
			sendSuccessJson(res, user, "注册成功！");
		} else {
			sendFailureMessage(res, "注册失败");
		}
	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @param user
	 *            包含查询条件的User对象
	 * @param res
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public void checkUserName(User user, HttpServletResponse res) {
		boolean result = false;// result为true表示可以使用
		try {
			result = userService.checkUserName(user);
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
		if (result) {
			sendSuccessJson(res, user, "用户名可以使用！");
		} else {
			sendFailureMessage(res, "用户名已存在！");
		}
	}

	/**
	 * 跳转到用户列表页面
	 * 
	 * @return 用户列表页面
	 */
	@RequestMapping(value = "/toUserList")
	public ModelAndView toUserList() {
		return forward("user/user_list", null);
	}

	/**
	 * 列出用户
	 * 
	 * @param user
	 *            包含查询条件的User对象
	 * @param res
	 */
	@RequestMapping(value = "/listUser", method = RequestMethod.POST)
	public void listUser(User user, HttpServletResponse res) {
		Datagrid<User> userDatagrid = new Datagrid<User>();
		try {
			userDatagrid = userService.listUser(user);
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
		if (userDatagrid != null) {
			// 向前台发送datagrid对象
			sendJson(res, userDatagrid);
		} else {
			sendFailureMessage(res, "当前没有用户！");
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            待添加的User对象
	 * @param res
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(User user, HttpServletResponse res) {
		try {
			user = userService.add(user);
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
		if (user != null) {
			sendSuccessJson(res, user, "添加用户成功！");
		} else {
			sendFailureMessage(res, "添加用户失败！");
		}
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 *            待更新的User对象
	 * @param res
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void edit(User user, HttpServletResponse res) {
		try {
			user = userService.edit(user);
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
		if (user != null) {
			sendSuccessJson(res, user, "修改用户成功！");
		} else {
			sendFailureMessage(res, "修改用户失败！");
		}
	}

	/**
	 * 删除用户，可以删除一个或多个用户
	 * 
	 * @param user
	 *            包含待删除用户id或ids的User对象
	 * @param res
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public void remove(User user, HttpServletResponse res) {
		try {
			userService.remove(user);
			sendSuccessJson(res, user, "删除用户成功！");
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
	}

	/**
	 * 主动抛出的ajax异常
	 * 
	 * @param res
	 */
	@RequestMapping(value = "/ajaxException", method = RequestMethod.POST)
	public void ajaxException(HttpServletResponse res) {
		try {
			userService.ajaxException();
		} catch (UserException e) {
			sendFailureMessage(res, e.getMessage());
		}
	}

	/**
	 * 主动抛出的跳转异常，抛出后由BackExceptionHandler统一处理
	 * 
	 * @return 抛出异常，返回null
	 * @throws UserException
	 *             自定义用户操作异常，抛出后由BackExceptionHandler统一处理
	 */
	@RequestMapping(value = "/forwardException", method = RequestMethod.GET)
	public ModelAndView forwardException() throws UserException {
		userService.forwardException();
		return null;
	}
}
