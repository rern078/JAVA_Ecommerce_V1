package com.example.firstProject.ecommerce.dto;

import com.example.firstProject.ecommerce.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
		@NotBlank String name,
		@Email @NotBlank String email,
		@NotBlank String password,
		UserRole role
) {
}
