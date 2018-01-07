package com.my.security.core.verification.captcha;

import com.my.security.core.properties.SecurityProperties;
import com.my.security.core.verification.captcha.image.ImageCaptchaGenerator;
import com.my.security.core.verification.captcha.sms.DefaultSMSCaptchaService;
import com.my.security.core.verification.captcha.sms.SMSCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(name = "imageCaptchaGenerator")
	public CaptchaGenerator imageCaptchaGenerator() {
		ImageCaptchaGenerator imageCaptchaGenerator = new ImageCaptchaGenerator();
		imageCaptchaGenerator.setSecurityProperties(securityProperties);
		return imageCaptchaGenerator;
	}

	@Bean
	@ConditionalOnMissingBean(SMSCaptchaService.class)
	public SMSCaptchaService smsCaptchaService() {
		return new DefaultSMSCaptchaService();
	}

}
