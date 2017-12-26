package com.my.web.controller;


import com.my.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

	private String path = "/home/klc/java/mysecurity/mysecuritydemo/src/test/java/com/my/web/controller";

	@PostMapping()
	public FileInfo upload(MultipartFile file) throws IOException {
		System.out.println("FileController->upload() --file name--: " + file.getName());
		System.out.println("FileController->upload() --file original name--: " + file.getOriginalFilename());
		System.out.println("FileController->upload() --file size--: " + file.getSize());

		// JUST FOR DEMO!!!!!

		// Get content from FileInputStream and upload anywhere
		// file.getInputStream();

		File localFile = new File(path, new Date().getTime() + ".txt");

		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		try (
				InputStream inputStream = new FileInputStream(new File(path, id + ".txt"));
				OutputStream outputStream = httpServletResponse.getOutputStream();
		) {
			httpServletResponse.setContentType("application/x-download");
			httpServletResponse.addHeader("Content-Disposition", "attachment;filename=test.txt");

			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();

		}
	}


}
