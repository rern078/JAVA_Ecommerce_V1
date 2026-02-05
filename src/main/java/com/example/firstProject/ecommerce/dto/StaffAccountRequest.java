package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.Email;

public record StaffAccountRequest(
		String firstName,
		String lastName,
		String phoneNumber,
		@Email String email,
		String password,
		Boolean active,
		String profileImg
) {
}
