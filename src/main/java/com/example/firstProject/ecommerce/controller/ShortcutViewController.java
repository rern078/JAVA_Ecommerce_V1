package com.example.firstProject.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShortcutViewController {
	@GetMapping("/products")
	public String products() {
		return "redirect:/dashboard/products";
	}

	@GetMapping("/customers")
	public String customers() {
		return "redirect:/dashboard/customers";
	}

	@GetMapping("/orders")
	public String orders() {
		return "redirect:/dashboard/orders";
	}
}
