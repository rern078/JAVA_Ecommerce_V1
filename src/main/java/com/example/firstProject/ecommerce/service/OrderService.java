package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.dto.CreateOrderRequest;
import com.example.firstProject.ecommerce.dto.OrderItemRequest;
import com.example.firstProject.ecommerce.exception.BadRequestException;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Customer;
import com.example.firstProject.ecommerce.model.Order;
import com.example.firstProject.ecommerce.model.OrderItem;
import com.example.firstProject.ecommerce.model.OrderStatus;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.repository.CustomerRepository;
import com.example.firstProject.ecommerce.repository.OrderRepository;
import com.example.firstProject.ecommerce.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository,
						CustomerRepository customerRepository,
						ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	public List<Order> listOrders(Long customerId) {
		if (customerId == null) {
			return orderRepository.findAll();
		}
		return orderRepository.findWithDetailsByCustomerId(customerId);
	}

	public Order getOrder(Long orderId) {
		return orderRepository.findWithDetailsById(orderId)
				.orElseThrow(() -> new NotFoundException("Order not found: " + orderId));
	}

	@Transactional
	public Order createOrder(CreateOrderRequest request) {
		Customer customer = customerRepository.findById(request.customerId())
				.orElseThrow(() -> new NotFoundException("Customer not found: " + request.customerId()));

		Order order = new Order(customer);
		List<OrderItem> items = new ArrayList<>();
		BigDecimal total = BigDecimal.ZERO;

		for (OrderItemRequest itemRequest : request.items()) {
			Product product = productRepository.findById(itemRequest.productId())
					.orElseThrow(() -> new NotFoundException("Product not found: " + itemRequest.productId()));

			if (itemRequest.quantity() <= 0) {
				throw new BadRequestException("Quantity must be greater than zero.");
			}
			if (product.getStock() < itemRequest.quantity()) {
				throw new BadRequestException("Insufficient stock for product: " + product.getName());
			}

			BigDecimal unitPrice = product.getPrice();
			BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(itemRequest.quantity()));
			product.setStock(product.getStock() - itemRequest.quantity());

			OrderItem orderItem = new OrderItem(order, product, itemRequest.quantity(), unitPrice, lineTotal);
			items.add(orderItem);
			total = total.add(lineTotal);
		}

		order.setItems(items);
		order.setTotal(total);
		return orderRepository.save(order);
	}

	@Transactional
	public Order updateStatus(Long orderId, OrderStatus status) {
		Order order = getOrder(orderId);
		OrderStatus previous = order.getStatus();
		if (previous != status) {
			order.setStatus(status);
		}

		if (status == OrderStatus.CANCELLED && previous != OrderStatus.CANCELLED) {
			for (OrderItem item : order.getItems()) {
				Product product = item.getProduct();
				product.setStock(product.getStock() + item.getQuantity());
			}
		}
		return orderRepository.save(order);
	}
}
