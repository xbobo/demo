package com.demo.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常
 */
public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 6910802627078585722L;

	public IncorrectCaptchaException() {
        super();
    }
	
	public IncorrectCaptchaException(String message) {
        super(message);
    }
	
	public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
	
	public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
