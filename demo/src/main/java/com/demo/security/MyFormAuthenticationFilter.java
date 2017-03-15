package com.demo.security;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.demo.app.conf.MsgConstant;
import com.demo.exception.IncorrectCaptchaException;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	/**
	 * 登录验证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
			Subject subject = getSubject(request, response);
			Session session = subject.getSession();
			// 获取用户密码输入错误的次数
			Object passwordErrorCount = session.getAttribute("passwordErrorCount");
			if (passwordErrorCount != null) {
				Integer errorCount = (Integer) passwordErrorCount;
				// 当密码错误次数大于等于5次校验验证码
				if (errorCount >= 5) {
					// doCaptchaValidate((HttpServletRequest) request, token);
				}
			}
			subject.login(token);// 正常验证
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	// 验证码校验
	private void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
		String captcha = (String) request.getSession().getAttribute(CheckCodeUtil.DEFAULT_CAPTCHA_PARAM);
		if(captcha == null){
			throw new IncorrectCaptchaException(MsgConstant.INCORRECT_CAPTCHA);
		}
		
		if (!captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException(MsgConstant.INCORRECT_CAPTCHA);
		}
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, getSuccessUrl());
		return false;
	}

	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	private String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, CheckCodeUtil.DEFAULT_CAPTCHA_PARAM);
	}
	
}
