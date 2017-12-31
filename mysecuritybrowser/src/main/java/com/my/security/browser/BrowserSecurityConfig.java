package com.my.security.browser;

import com.my.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler myAuthenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// for HTTP basic authentication
		//http.httpBasic()

		// Use HTML Form to login
		http.formLogin()
			// Define the login page
			.loginPage("/auth/login")
			// Define login process url
			.loginProcessingUrl("/auth/login")
			// Assign customize AuthenticationSuccessHandler
			.successHandler(myAuthenticationSuccessHandler)
			// Assign customize AuthenticationFailureHandler
			.failureHandler(myAuthenticationFailureHandler)
			.and()
			// Below are the config of authorizing requests
			.authorizeRequests()
			// Without any authorizations in login page
			.antMatchers(
					"/auth/login",
					securityProperties.getBrowser().getLoginPage()
			).permitAll()
			// All requests
			.anyRequest()
			// Must be authenticated
			.authenticated()
			.and()
			// DEMO ONLY!!!!!!
			.csrf().disable();
	}
}
