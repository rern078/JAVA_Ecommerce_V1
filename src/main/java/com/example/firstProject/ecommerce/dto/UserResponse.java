package com.example.firstProject.ecommerce.dto;

import com.example.firstProject.ecommerce.model.UserRole;
import java.time.LocalDateTime;

public record UserResponse(
		Long id,
		String name,
		String email,
		UserRole role,
		LocalDateTime createdAt
) {
}
