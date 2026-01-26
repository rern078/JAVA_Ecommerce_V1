package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record UpdateProductRequest(
		@NotBlank String name,
		String description,
		@NotNull @Positive BigDecimal price,
		@PositiveOrZero int stock,
		Long supplierId,
		Long categoryId,
		String sku,
		String idSku,
		String vendorProductId,
		String quantityPerUnit,
		BigDecimal msrp,
		String availableSize,
		String availableColors,
		String size,
		String color,
		BigDecimal discount,
		BigDecimal unitWeight,
		Integer unitsOnOrder,
		Integer reorderLevel,
		Boolean productAvailable,
		Boolean discountAvailable,
		Boolean currentOrder,
		String picture,
		String productGallery,
		Integer ranking,
		String note
) {
}
