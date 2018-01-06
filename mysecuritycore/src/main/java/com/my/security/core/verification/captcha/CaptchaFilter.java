package com.my.security.core.verification.captcha;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CaptchaFilter extends OncePerRequestFilter {

	private AuthenticationFailureHandler authenticationFailureHandler;
	private SessionStrategy sessionStrategy	= new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		/**
		 *  If it is a login request
		 *  	then validate captcha
		 *  		If the captcha entered by user is verified
		 *  			then go to next filter
		 */
		if (StringUtils.equals("/auth/login", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			try {
				verify(new ServletWebRequest(request));
			} catch (CaptchaException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private void verify(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCaptcha codeInSession = (ImageCaptcha) sessionStrategy.getAttribute(request,
				CaptchaController.SESSION_KEY);

		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCaptcha");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new CaptchaException("Captcha cannot be null!");
		}

		if(codeInSession == null){
			throw new CaptchaException("Captcha is not exist!");
		}

		if(codeInSession.isExpired()){
			sessionStrategy.removeAttribute(request, CaptchaController.SESSION_KEY);
			throw new CaptchaException("Captcha has been expired!");
		}

		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new CaptchaException("Captcha are not match!");
		}

		sessionStrategy.removeAttribute(request, CaptchaController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}
}
