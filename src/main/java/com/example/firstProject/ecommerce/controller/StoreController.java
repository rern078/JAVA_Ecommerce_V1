package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.service.CatalogService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {
	private final CatalogService catalogService;

	public StoreController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping
	public String storeHome(Model model) {
		List<Product> products = catalogService.listProducts();
		model.addAttribute("products", products);
		return "store";
	}
}
