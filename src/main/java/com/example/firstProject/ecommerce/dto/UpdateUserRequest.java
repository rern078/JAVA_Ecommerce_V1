package com.example.firstProject.ecommerce.dto;

import com.example.firstProject.ecommerce.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
		@NotBlank String name,
		@Email @NotBlank String email,
		String password,
		UserRole role
) {
}
