package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.CategoryResponse;
import com.example.firstProject.ecommerce.dto.CreateCategoryRequest;
import com.example.firstProject.ecommerce.dto.UpdateCategoryRequest;
import com.example.firstProject.ecommerce.model.Category;
import com.example.firstProject.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public List<CategoryResponse> listCategories() {
		return categoryService.listCategories().stream()
				.map(CategoryController::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public CategoryResponse getCategory(@PathVariable Long id) {
		return toResponse(categoryService.getCategory(id));
	}

	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
		Category created = categoryService.createCategory(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	@PutMapping("/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id,
										  @Valid @RequestBody UpdateCategoryRequest request) {
		return toResponse(categoryService.updateCategory(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

	private static CategoryResponse toResponse(Category category) {
		return new CategoryResponse(
				category.getId(),
				category.getCategoryName(),
				category.getDescription(),
				category.getPicture(),
				category.getActive()
		);
	}
}
