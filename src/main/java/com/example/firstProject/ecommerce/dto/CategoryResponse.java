package com.example.firstProject.ecommerce.dto;

public record CategoryResponse(
		Long id,
		String categoryName,
		String description,
		String picture,
		Boolean active
) {
}
