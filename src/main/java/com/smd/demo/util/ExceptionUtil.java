package com.smd.demo.util;

/**
 * 异常工具类
 * 
 * 主要是获得异常信息堆栈信息的字符串，用于向控制台打印或输出到文件中
 *
 */
public class ExceptionUtil {
	
	/**
	 * 系统行分隔符
	 */
	public static final String CR = System.getProperty("line.separator");
	
	/**
	 * 返回错误信息的堆栈
	 * @param e 异常对象
	 * @return 异常堆栈拼接而成的字符串
	 */
	public static String printStackTrace(Throwable e){
		StringBuffer sb = new StringBuffer();
		sb.append(e.toString() + CR);
	    StackTraceElement[] trace = e.getStackTrace();
        for (int i=0; i < trace.length; i++){
        	sb.append("\tat " + trace[i] + CR);
        }
		return sb.toString();
	}
}
