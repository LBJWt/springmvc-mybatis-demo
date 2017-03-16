package com.smd.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转拦截器，防止未登录用户在浏览器地址栏直接输入地址进行跳转
 */
public class ForwardInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception e) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView e) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		String uri = req.getRequestURI().toString();
		if (uri != null && !(uri.contains("/toRegister.do") || uri.contains("/login.do") || uri.contains("/checkUserName.do") || uri.contains("/register.do"))) {// 过滤无需拦截的uri
			if (req.getSession().getAttribute("user") != null) {// 登录用户不用拦截
				return true;
			} else {
				// 未登录用户拦截返回登录页
				req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, res);
				return false;
			}
		} else {
			return true;
		}
	}

}
