package com.smd.demo.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 定义controller的公共操作，包括跳转、向页面发送json等
 */
public class BaseController {

	// 定义一些常量，作为返回页面的map的key值
	public final static String SUCCESS = "success";
	public final static String MSG = "msg";
	public final static String OBJ = "obj";
	public final static String DATA = "data";
	public final static String LOGOUT_FLAG = "logoutFlag";

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 页面跳转
	 * 
	 * @param viewName
	 *            跳转目标页面的jsp文件名（无后缀）
	 * @param context
	 *            目标页面需要的响应对象
	 * @param type
	 *            数据格式
	 * @return 包含目标页面和响应对象的ModelAndView对象
	 */
	public ModelAndView forward(String viewName, Object context, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("json".equalsIgnoreCase(type)) {
			String json = null;
			try {
				json = JSON.toJSONString(context);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			map.put("json", json);
		}
		return new ModelAndView(viewName, map);
	}

	/**
	 * 页面跳转
	 * 
	 * @param viewName
	 *            跳转目标页面的jsp文件名（无后缀）
	 * @param context
	 *            响应Map对象
	 * @return 包含目标页面和响应对象的ModelAndView对象
	 */
	public ModelAndView forward(String viewName, Map<String, ? extends Object> context) {
		return new ModelAndView(viewName, context);
	}

	/**
	 * 在新页面提示错误信息
	 * 
	 * @param errMsg
	 *            错误信息
	 * @return 错误信息页面
	 */
	public ModelAndView error(String errMsg) {
		return new ModelAndView(errMsg);
	}

	/**
	 * 向页面发送成功信息
	 * 
	 * @param response
	 * @param message
	 *            操作成功提示信息
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		writeJson(response, result);
	}

	/**
	 * 向页面发送响应对象（无成功状态和消息）
	 * 
	 * @param response
	 * @param obj
	 *            响应对象
	 */
	public void sendJson(HttpServletResponse response, Object obj) {
		writeJson(response, obj);
	}

	/**
	 * 发送成功的信息和对象
	 * 
	 * @param response
	 * @param obj
	 *            响应对象
	 * @param message
	 *            操作成功提示信息
	 */
	public void sendSuccessJson(HttpServletResponse response, Object obj, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		result.put(OBJ, obj);
		writeJson(response, result);
	}

	/**
	 * 提示失败信息
	 * 
	 * @param response
	 * @param message
	 *            操作失败提示信息
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		writeJson(response, result);
	}

	/**
	 * 发送json格式的表格数据 ：将Map转成json
	 * 
	 * @param response
	 * @param pager
	 *            响应对象
	 */
	public void sendTableDataJson(HttpServletResponse response, Object pager) {
		writeJson(response, pager);
	}

	/**
	 * 发送json格式的数组数据：将Collection转成json
	 * 
	 * @param response
	 * @param pager
	 *            响应Collection对象
	 */
	public void sendArrayDataJson(HttpServletResponse response, Object pager) {
		writeArrayJson(response, pager);
	}

	/**
	 * 将对象转为json串，并发送给前台页面
	 * 
	 * @param response
	 * @param obj
	 *            响应对象
	 */
	private void writeJson(HttpServletResponse response, Object obj) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.write(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转为json数组，并发送给前台页面
	 * 
	 * @param response
	 * @param obj
	 *            响应对象
	 */
	private void writeArrayJson(HttpServletResponse response, Object pager) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.write(JSONArray.toJSONString(pager, SerializerFeature.WriteDateUseDateFormat));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
