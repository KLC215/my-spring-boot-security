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

import java.util.Date;

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
	public void it_can_query_all_users() throws Exception {
		String result = mockMvc
				.perform(
						MockMvcRequestBuilders
								.get("/users")
								/* UserQueryParameters */
								.param("username", "user1")
								.param("age", "20")
								.param("maxAge", "99")
								.param("somethingYouWantTo", "something")
								/* Pageable */
								//.param("size", "15")
								//.param("page", "3")
								//.param("sort", "age,desc")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(
						MockMvcResultMatchers
								.status()
								.isOk()
				)
				/* https://github.com/json-path/JsonPath */
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.length()")
								.value(3)
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println(result);
	}

	@Test
	public void it_can_query_a_user() throws Exception {
		String result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/users/1")
											  .contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(
						MockMvcResultMatchers.status()
											 .isOk()
				)
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.username")
											 .value("tom")
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println(result);
	}

	@Test
	public void it_cannot_query_a_user() throws Exception {
		mockMvc
				.perform(
						MockMvcRequestBuilders
								.get("/users/a")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(
						MockMvcResultMatchers
								.status()
								.is4xxClientError()
				);
	}

	@Test
	public void it_can_create_a_user() throws Exception {
		Date date = new Date();
		String content = "{\"username\":\"tom\",\"password\":\"password\",\"dob\":" + date.getTime() + "}";

		String result = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/users")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(content)

				)
				.andExpect(
						MockMvcResultMatchers
								.status()
								.isOk()
				)
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.id")
								.value(1)
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println(result);
	}

}
