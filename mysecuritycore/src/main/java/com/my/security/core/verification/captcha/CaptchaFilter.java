package com.my.security.core.verification.captcha;

import com.my.security.core.properties.SecurityProperties;
import com.my.security.core.verification.captcha.image.ImageCaptcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CaptchaFilter extends OncePerRequestFilter implements InitializingBean {

	private AuthenticationFailureHandler authenticationFailureHandler;
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	private Set<String> urls = new HashSet<>();
	private SecurityProperties securityProperties;
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	private final String SESSION_IMAGE_KEY = CaptchaProcessor.SESSION_KEY_PREFIX + "IMAGE";

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCaptcha().getImage().getUrl(), ",");

		// Assign urls in application properties to a set
		urls.addAll(Arrays.asList(configUrls));

		// `/auth/login` must be verified
		urls.add("/auth/login");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		boolean goVerify = false;

		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				goVerify = true;
			}
		}

		/**
		 *  If it is a login request
		 *  	then validate captcha
		 *  		If the captcha entered by user is verified
		 *  			then go to next filter
		 */
		if (goVerify) {
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
				SESSION_IMAGE_KEY);

		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCaptcha");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new CaptchaException("Captcha cannot be null!");
		}

		if (codeInSession == null) {
			throw new CaptchaException("Captcha is not exist!");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, SESSION_IMAGE_KEY);
			throw new CaptchaException("Captcha has been expired!");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new CaptchaException("Captcha are not match!");
		}

		sessionStrategy.removeAttribute(request, SESSION_IMAGE_KEY);
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

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
}
