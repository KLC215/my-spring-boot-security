package com.my.security.core.properties;

public class CaptchaProperties {

	private ImageCaptchaProperties image = new ImageCaptchaProperties();
	private SMSCaptchaProperties sms = new SMSCaptchaProperties();

	public ImageCaptchaProperties getImage() {
		return image;
	}

	public void setImage(ImageCaptchaProperties image) {
		this.image = image;
	}

	public SMSCaptchaProperties getSms() {
		return sms;
	}

	public void setSms(SMSCaptchaProperties sms) {
		this.sms = sms;
	}
}
