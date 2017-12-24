package com.my.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(ctx)
				.build();
	}

	@Test
	public void it_can_query_users() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/users")
						/* UserQueryParameters */
						.param("username", "user1")
						.param("age", "20")
						.param("maxAge", "99")
						.param("somethingYouWantTo", "something")
						/* Pageable */
//						.param("size", "15")
//						.param("page", "3")
//						.param("sort", "age,desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   /* https://github.com/json-path/JsonPath */
			   .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

}
