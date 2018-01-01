package com.my.security.browser;

import com.my.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler myAuthenticationFailureHandler;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {

		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		//jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;

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
			// Below are the config of remember me
			.rememberMe()
			// Assign a TokenRepository
			.tokenRepository(persistentTokenRepository())
			// Set expiration time of the token
			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeExpirationTime())
			// Assign a UserDetailsService for login
			.userDetailsService(userDetailsService)
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
