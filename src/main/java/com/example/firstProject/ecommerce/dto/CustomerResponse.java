package com.example.firstProject.ecommerce.dto;

import java.time.LocalDateTime;

public record CustomerResponse(
		Long id,
		String name,
		String email,
		String address,
		LocalDateTime createdAt
) {
}
