package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.Customer;
import com.example.firstProject.ecommerce.model.Order;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.service.AuthService;
import com.example.firstProject.ecommerce.service.CatalogService;
import com.example.firstProject.ecommerce.service.CustomerService;
import com.example.firstProject.ecommerce.service.OrderService;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	private final CatalogService catalogService;
	private final CustomerService customerService;
	private final OrderService orderService;
	private final AuthService authService;

	public ViewController(CatalogService catalogService,
						  CustomerService customerService,
						  OrderService orderService,
						  AuthService authService) {
		this.catalogService = catalogService;
		this.customerService = customerService;
		this.orderService = orderService;
		this.authService = authService;
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
}
