package com.my.security.core.verification.captcha;

import com.my.security.core.properties.SecurityProperties;
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

}
