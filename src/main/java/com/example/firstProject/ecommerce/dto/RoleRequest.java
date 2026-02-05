package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
		@NotBlank String roleName,
		String privileges
) {
}
