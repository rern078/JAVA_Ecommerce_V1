package com.example.firstProject.ecommerce.dto;

public record ShippingRequest(
		String name,
		Boolean active,
		String iconPath
) {
}
