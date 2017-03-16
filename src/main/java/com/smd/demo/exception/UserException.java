package com.smd.demo.exception;

/**
 * 自定义的异常，捕获用户操作时发生的后台错误
 */
public class UserException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造一个用户操作异常
	 */
	public UserException() {
		super();
	}

	/**
	 * 用message构造一个用户操作异常
	 * 
	 * @param message
	 *            反馈给用户看的消息
	 */
	public UserException(String message) {
		super("用户异常：" + message);
	}

	/**
	 * 用cause构造一个用户操作异常
	 * 
	 * @param cause
	 *            异常本身
	 */
	public UserException(Throwable cause) {
		super(cause);
	}

	/**
	 * 用message和cause构造一个用户操作异常
	 * 
	 * @param message
	 *            反馈给用户看的消息
	 * @param cause
	 *            异常本身
	 */
	public UserException(String message, Throwable cause) {
		super("用户异常：" + message, cause);
	}
}
