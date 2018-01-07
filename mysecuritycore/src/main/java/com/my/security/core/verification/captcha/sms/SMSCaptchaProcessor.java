package com.my.security.core.verification.captcha.sms;

import com.my.security.core.verification.captcha.AbstractCaptchaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCaptchaProcessor")
public class SMSCaptchaProcessor extends AbstractCaptchaProcessor<SMSCaptcha> {

	@Autowired
	private SMSCaptchaService smsCaptchaService;

	@Override
	protected void send(ServletWebRequest request, SMSCaptcha captcha) throws Exception {
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
		smsCaptchaService.sendSMS(mobile, captcha.getCode());
	}

}
