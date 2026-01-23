package com.example.firstProject.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
		Long id,
		String name,
		String description,
		BigDecimal price,
		int stock,
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
		Integer ranking,
		String note,
		LocalDateTime createdAt
) {
}
