package com.my.web.controller;

import com.my.dto.User;
import com.my.dto.UserQueryParameters;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping()
	public List<User> query(UserQueryParameters query, @PageableDefault(size = 10, page = 0, sort = "username,asc") Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(query, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<User> users = new ArrayList<>();

		users.add(new User());
		users.add(new User());
		users.add(new User());

		return users;
	}

}
