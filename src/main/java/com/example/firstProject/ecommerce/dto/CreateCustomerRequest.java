package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
		@NotBlank String name,
		@Email @NotBlank String email,
		String address
) {
}
