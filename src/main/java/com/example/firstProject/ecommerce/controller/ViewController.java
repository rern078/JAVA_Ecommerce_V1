package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.Customer;
import com.example.firstProject.ecommerce.model.Notification;
import com.example.firstProject.ecommerce.model.Order;
import com.example.firstProject.ecommerce.model.OrderItem;
import com.example.firstProject.ecommerce.model.OrderStatusEntity;
import com.example.firstProject.ecommerce.model.Payment;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.service.AuthService;
import com.example.firstProject.ecommerce.service.CatalogService;
import com.example.firstProject.ecommerce.service.CategoryService;
import com.example.firstProject.ecommerce.service.CustomerService;
import com.example.firstProject.ecommerce.service.OrderService;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class ViewController {
	private final CatalogService catalogService;
	private final CustomerService customerService;
	private final OrderService orderService;
	private final AuthService authService;
	private final CategoryService categoryService;
	private final EntityManager entityManager;

	public ViewController(CatalogService catalogService,
						  CustomerService customerService,
						  OrderService orderService,
						  AuthService authService,
						  CategoryService categoryService,
						  EntityManager entityManager) {
		this.catalogService = catalogService;
		this.customerService = customerService;
		this.orderService = orderService;
		this.authService = authService;
		this.categoryService = categoryService;
		this.entityManager = entityManager;
	}

	@GetMapping
	public String index(Model model, HttpSession session) {
		String email = (String) session.getAttribute("userEmail");
		Optional<String> name = authService.getDisplayName(email);
		model.addAttribute("displayName", name.orElse(email));
		return "index";
	}

	@GetMapping("/products")
	public String products(Model model) {
		List<Product> products = catalogService.listProducts();
		model.addAttribute("products", products);
		model.addAttribute("categories", categoryService.listCategories());
		return "products";
	}

	@GetMapping("/customers")
	public String customers(Model model) {
		List<Customer> customers = customerService.listCustomers();
		model.addAttribute("customers", customers);
		return "customers";
	}

	@GetMapping("/orders")
	public String orders(Model model) {
		List<Order> orders = orderService.listOrders(null);
		model.addAttribute("orders", orders);
		return "orders";
	}

	@GetMapping("/categories")
	public String categories(Model model) {
		model.addAttribute("categories", categoryService.listCategories());
		return "categories";
	}

	@GetMapping("/notifications")
	public String notifications(Model model) {
		List<Notification> notifications = entityManager
				.createQuery("from Notification", Notification.class)
				.getResultList();
		model.addAttribute("notifications", notifications);
		return "notifications";
	}

	@GetMapping("/order-items")
	public String orderItems(Model model) {
		List<OrderItem> orderItems = entityManager
				.createQuery("from OrderItem", OrderItem.class)
				.getResultList();
		model.addAttribute("orderItems", orderItems);
		return "order-items";
	}

	@GetMapping("/order-statuses")
	public String orderStatuses(Model model) {
		List<OrderStatusEntity> orderStatuses = entityManager
				.createQuery("from OrderStatusEntity", OrderStatusEntity.class)
				.getResultList();
		model.addAttribute("orderStatuses", orderStatuses);
		return "order-statuses";
	}

	@GetMapping("/payments")
	public String payments(Model model) {
		List<Payment> payments = entityManager
				.createQuery("from Payment", Payment.class)
				.getResultList();
		model.addAttribute("payments", payments);
		return "payments";
	}
}
