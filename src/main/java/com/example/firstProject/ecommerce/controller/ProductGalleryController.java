package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Gallery;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.repository.GalleryRepository;
import com.example.firstProject.ecommerce.repository.ProductRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products/{productId}/gallery")
public class ProductGalleryController {
	private static final Path PRODUCT_GALLERY_DIR =
			Paths.get("src/main/resources/static/uploads/products");

	private final ProductRepository productRepository;
	private final GalleryRepository galleryRepository;

	public ProductGalleryController(ProductRepository productRepository,
									GalleryRepository galleryRepository) {
		this.productRepository = productRepository;
		this.galleryRepository = galleryRepository;
	}

	@GetMapping
	public List<Map<String, Object>> listGallery(@PathVariable Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new NotFoundException("Product not found: " + productId);
		}
		return galleryRepository.findByProductIdOrderByDisplayOrderAsc(productId).stream()
				.map(gallery -> Map.<String, Object>of(
						"id", gallery.getId(),
						"url", gallery.getImagePath()
				))
				.toList();
	}

	@PostMapping
	public ResponseEntity<?> uploadGallery(@PathVariable Long productId,
										   @RequestParam("files") List<MultipartFile> files) {
		if (files == null || files.isEmpty()) {
			return ResponseEntity.badRequest().body("Files are required.");
		}
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("Product not found: " + productId));

		try {
			Path productDir = PRODUCT_GALLERY_DIR.resolve(String.valueOf(productId));
			Files.createDirectories(productDir);
			long currentOrder = galleryRepository.countByProductId(productId);
			for (int i = 0; i < files.size(); i++) {
				MultipartFile file = files.get(i);
				if (file == null || file.isEmpty()) {
					continue;
				}
				String filename = buildFilename(file.getOriginalFilename());
				Path target = productDir.resolve(filename);
				Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
				Gallery gallery = new Gallery();
				gallery.setProduct(product);
				gallery.setImagePath("/uploads/products/" + productId + "/" + filename);
				gallery.setThumbnail(false);
				gallery.setDisplayOrder((short) (currentOrder + i));
				gallery.setCreatedAt(LocalDateTime.now());
				gallery.setUpdatedAt(LocalDateTime.now());
				galleryRepository.save(gallery);
			}
			Map<String, Object> response = new HashMap<>();
			response.put("status", "ok");
			return ResponseEntity.ok(response);
		} catch (IOException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to store files.");
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
