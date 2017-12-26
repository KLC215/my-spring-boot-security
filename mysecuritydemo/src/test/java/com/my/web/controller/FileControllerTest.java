package com.my.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {

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
	public void it_can_upload_a_file() throws Exception {
		String result = mockMvc
				.perform(
						MockMvcRequestBuilders
								.fileUpload("/file")
								.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "Hello Upload".getBytes()))
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(
						MockMvcResultMatchers
								.status()
								.isOk()
				)
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println(result);
	}
}
