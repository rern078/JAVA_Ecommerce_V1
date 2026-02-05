package com.example.firstProject.ecommerce.dto;

public record SlideshowRequest(
		String destinationUrl,
		String imageUrl,
		Short clicks
) {
}
