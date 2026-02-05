package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(
		@NotBlank String companyName,
		String contactName,
		String contactTitle,
		String address1,
		String address2,
		String city,
		String state,
		String postalCode,
		String country,
		String phone,
		String fax,
		String email,
		String url,
		String notes
) {
}
