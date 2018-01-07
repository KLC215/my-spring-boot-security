package com.my.security.core.verification.captcha;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

public abstract class AbstractCaptchaProcessor<C extends Captcha> implements CaptchaProcessor {

	/**
	 * Utils for controlling session
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	/**
	 * Collect all {@link CaptchaGenerator} interface in the application
	 */
	@Autowired
	private Map<String, CaptchaGenerator> captchaGeneratorMap;


	/**
	 * Generic creating captcha
	 *
	 * @param request
	 *
	 * @throws Exception
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		// 1. Generate captcha
		C captcha = generate(request);
		// 2. Save captcha to session
		save(request, captcha);
		// 3. Send captcha
		send(request, captcha);

	}

	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getProcessorType(request);
		CaptchaGenerator captchaGenerator = captchaGeneratorMap.get(type + "CaptchaGenerator");
		return (C) captchaGenerator.generate(request);
	}

	private void save(ServletWebRequest request, C captcha) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), captcha);
	}

	protected abstract void send(ServletWebRequest request, C captcha) throws Exception;

	/**
	 * Get captcha type from request url
	 *
	 * @param request
	 *
	 * @return
	 */
	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/captcha/");
	}
}
