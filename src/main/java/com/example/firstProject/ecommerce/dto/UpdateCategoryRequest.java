package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
		@NotBlank String categoryName,
		String description,
		String picture,
		Boolean active
) {
}
