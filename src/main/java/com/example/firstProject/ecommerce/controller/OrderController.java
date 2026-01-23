package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.CreateOrderRequest;
import com.example.firstProject.ecommerce.dto.OrderItemResponse;
import com.example.firstProject.ecommerce.dto.OrderResponse;
import com.example.firstProject.ecommerce.dto.UpdateOrderStatusRequest;
import com.example.firstProject.ecommerce.model.Order;
import com.example.firstProject.ecommerce.model.OrderItem;
import com.example.firstProject.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<OrderResponse> listOrders(@RequestParam(required = false) Long customerId) {
		return orderService.listOrders(customerId).stream()
				.map(OrderController::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public OrderResponse getOrder(@PathVariable Long id) {
		return toResponse(orderService.getOrder(id));
	}

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
		Order created = orderService.createOrder(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	@PatchMapping("/{id}/status")
	public OrderResponse updateStatus(@PathVariable Long id,
									  @Valid @RequestBody UpdateOrderStatusRequest request) {
		return toResponse(orderService.updateStatus(id, request.status()));
	}

	private static OrderResponse toResponse(Order order) {
		List<OrderItemResponse> items = order.getItems().stream()
				.map(OrderController::toResponse)
				.toList();

		return new OrderResponse(
				order.getId(),
				order.getCustomer().getId(),
				order.getStatus(),
				order.getTotal(),
				items,
				order.getCreatedAt()
		);
	}

	private static OrderItemResponse toResponse(OrderItem item) {
		return new OrderItemResponse(
				item.getProduct().getId(),
				item.getProduct().getName(),
				item.getQuantity(),
				item.getUnitPrice(),
				item.getLineTotal()
		);
	}
}
