package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
		@NotBlank String categoryName,
		String description,
		String picture,
		Boolean active
) {
}
