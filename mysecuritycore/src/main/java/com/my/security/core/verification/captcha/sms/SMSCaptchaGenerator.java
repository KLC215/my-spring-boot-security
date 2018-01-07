package com.my.security.core.verification.captcha.sms;

import com.my.security.core.properties.SecurityProperties;
import com.my.security.core.verification.captcha.CaptchaGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCaptchaGenerator")
public class SMSCaptchaGenerator implements CaptchaGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public SMSCaptcha generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getSms().getLength());
		return new SMSCaptcha(code, securityProperties.getCaptcha().getSms().getExpireIn());
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
