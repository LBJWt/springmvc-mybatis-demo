package com.smd.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.smd.demo.util.ExceptionUtil;

public class BackExceptionHandler implements HandlerExceptionResolver {

	Logger logger = Logger.getLogger(BackExceptionHandler.class); // log4j日志对象

	/**
	 * 统一处理程序中未被捕获的异常
	 * 
	 * @param request
	 *            
	 * @param response
	 *            
	 * @param handler
	 * 
	 * @param ex
	 *            异常
	 * @return 展示异常消息的错误页面
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		model.put("msg", ex.getMessage());
		// // 根据不同错误转向不同页面
		// if(ex instanceof FaceTemplateException) {
		// return new ModelAndView("faceTemplateException", model);
		// }else if(ex instanceof FileMongoException) {
		// return new ModelAndView("fileMongoException", model);
		// } else if(ex instanceof MetadataInfoException) {
		// return new ModelAndView("metadataInfoException", model);
		// } else {
		// return new ModelAndView("500", model);
		// }
		logger.error(ExceptionUtil.printStackTrace(ex));// 将日志写入error.log
		return new ModelAndView("/error/exception_msg", model);
	}

}
