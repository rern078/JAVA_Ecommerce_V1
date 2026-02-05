package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record ShipperRequest(
		@NotBlank String companyName,
		String phone
) {
}
