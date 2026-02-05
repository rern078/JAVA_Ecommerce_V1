package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record SellRequest(
		@NotNull Long productId,
		Float price,
		Short quantity
) {
}
