package com.example.firstProject.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {
	private static final Path CATEGORY_UPLOAD_DIR =
			Paths.get("src/main/resources/static/uploads/categories");
	private static final Path PRODUCT_UPLOAD_DIR =
			Paths.get("src/main/resources/static/uploads/products");

	@PostMapping("/categories")
	public ResponseEntity<?> uploadCategoryImage(@RequestParam("file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is required.");
		}

		try {
			Files.createDirectories(CATEGORY_UPLOAD_DIR);
			String filename = buildFilename(file.getOriginalFilename());
			Path target = CATEGORY_UPLOAD_DIR.resolve(filename);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.ok(Map.of("url", "/uploads/categories/" + filename));
		} catch (IOException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to store file.");
		}
	}

	@PostMapping("/products")
	public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is required.");
		}

		try {
			Files.createDirectories(PRODUCT_UPLOAD_DIR);
			String filename = buildFilename(file.getOriginalFilename());
			Path target = PRODUCT_UPLOAD_DIR.resolve(filename);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.ok(Map.of("url", "/uploads/products/" + filename));
		} catch (IOException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to store file.");
		}
	}

	private String buildFilename(String originalName) {
		String extension = "";
		if (originalName != null) {
			int lastDot = originalName.lastIndexOf('.');
			if (lastDot > -1 && lastDot < originalName.length() - 1) {
				extension = originalName.substring(lastDot);
			}
		}
		return UUID.randomUUID() + extension;
	}
}
