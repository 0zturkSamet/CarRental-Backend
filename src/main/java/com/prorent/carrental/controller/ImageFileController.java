package com.prorent.carrental.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prorent.carrental.domain.ImageFile;
import com.prorent.carrental.exception.UploadImageException;
import com.prorent.carrental.service.ImageFileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/files")
public class ImageFileController {

	private final ImageFileService imageFileService;

	@PostMapping("/upload")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {

		try {
			ImageFile imageFile = imageFileService.save(file);
			Map<String, String> map = new HashMap<>();
			map.put("imageId", imageFile.getId());
			return ResponseEntity.ok(map);
		} 
		catch (IOException e) {
			
			throw new UploadImageException("Could not upload image file");
		}

	}

}
