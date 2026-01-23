package com.example.firstProject.ecommerce.dto;

import com.example.firstProject.ecommerce.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
		@NotNull OrderStatus status
) {
}
