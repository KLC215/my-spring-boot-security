package com.my.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// GET DETAILS FROM DATABASE!!!

		// According username entered by user to get user's details from database
		logger.info("Login Username: " + username);

		// Return Spring User Class
		return new User(
				username,
				passwordEncoder.encode("admin"),
				true,
				true,
				true,
				true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
		);
	}
}
