package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record TagRequest(
		@NotBlank String tagName,
		String icon
) {
}
