package com.my.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.my.dto.User;
import com.my.dto.UserQueryParameters;
import com.my.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping()
	@JsonView(User.UserGeneralJsonView.class)
	public List<User> index(UserQueryParameters query, @PageableDefault(size = 10, page = 0, sort = "username,asc") Pageable pageable) {
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

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailJsonView.class)
	public User show(@PathVariable Integer id) {
		throw new UserNotExistException(id);
		/*User user = new User();
		user.setUsername("tom");
		return user;*/
	}

	@PostMapping()
	public User store(@Valid @RequestBody User user) {

//		if (errors.hasErrors()) {
//			errors.getAllErrors()
//				  .forEach(error -> System.out.println(error.getDefaultMessage()));
//		}

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getDob());

		user.setId(1);
		return user;
	}

	@PutMapping("/{id:\\d+}")
	@JsonView(User.UserDetailJsonView.class)
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors()
				  .forEach(error -> {
//					  FieldError fieldError = (FieldError) error;
//					  String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
					  System.out.println(error.getDefaultMessage());
				  });
		}

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getDob());

		user.setId(1);
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void destroy(@PathVariable Integer id) {
		System.out.println(id);
	}

}
