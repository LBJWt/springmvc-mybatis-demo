package com.smd.demo.exception;

import org.apache.log4j.Logger;

import com.smd.demo.util.ExceptionUtil;

/**
 * 系统中自定义异常的父类
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 3912969116333471855L;

	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Constructs a new throwable with null as its detail message.
	 */
	public BaseException() {
		super();
	}

	/**
	 * Constructs a new throwable with the specified detail message.
	 * 
	 * @param message
	 *            - the detail message. The detail message is saved for later
	 *            retrieval by the getMessage() method.
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * Constructs a new throwable with the specified cause and a detail message
	 * of (cause==null ? null : cause.toString()) (which typically contains the
	 * class and detail message of cause).
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            getCause() method). (A null value is permitted, and indicates
	 *            that the cause is nonexistent or unknown.)
	 */
	public BaseException(Throwable cause) {
		super(cause);
		logger.error(ExceptionUtil.printStackTrace(cause));// 打印错误日志到控制台和文件
	}

	/**
	 * Constructs a new throwable with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            getMessage() method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            getCause() method). (A null value is permitted, and indicates
	 *            that the cause is nonexistent or unknown.)
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		logger.error(ExceptionUtil.printStackTrace(cause));// 打印错误日志到控制台和文件
	}
}