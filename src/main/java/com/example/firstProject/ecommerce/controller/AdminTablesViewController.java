package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.model.Role;
import com.example.firstProject.ecommerce.model.Sell;
import com.example.firstProject.ecommerce.model.Shipper;
import com.example.firstProject.ecommerce.model.Shipping;
import com.example.firstProject.ecommerce.model.Slideshow;
import com.example.firstProject.ecommerce.model.StaffAccount;
import com.example.firstProject.ecommerce.model.StaffRole;
import com.example.firstProject.ecommerce.model.Supplier;
import com.example.firstProject.ecommerce.model.Tag;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AdminTablesViewController {
	private final EntityManager entityManager;

	public AdminTablesViewController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GetMapping("/roles")
	public String roles(Model model) {
		List<Role> roles = entityManager.createQuery("from Role", Role.class).getResultList();
		model.addAttribute("roles", roles);
		return "roles";
	}

	@GetMapping("/sells")
	public String sells(Model model) {
		List<Sell> sells = entityManager.createQuery("from Sell", Sell.class).getResultList();
		List<Product> products = entityManager.createQuery("from Product", Product.class).getResultList();
		model.addAttribute("sells", sells);
		model.addAttribute("products", products);
		return "sells";
	}

	@GetMapping("/shippers")
	public String shippers(Model model) {
		List<Shipper> shippers = entityManager.createQuery("from Shipper", Shipper.class).getResultList();
		model.addAttribute("shippers", shippers);
		return "shippers";
	}

	@GetMapping("/shippings")
	public String shippings(Model model) {
		List<Shipping> shippings = entityManager.createQuery("from Shipping", Shipping.class).getResultList();
		model.addAttribute("shippings", shippings);
		return "shippings";
	}

	@GetMapping("/slideshows")
	public String slideshows(Model model) {
		List<Slideshow> slideshows = entityManager.createQuery("from Slideshow", Slideshow.class).getResultList();
		model.addAttribute("slideshows", slideshows);
		return "slideshows";
	}

	@GetMapping("/staff-accounts")
	public String staffAccounts(Model model) {
		List<StaffAccount> staffAccounts = entityManager.createQuery("from StaffAccount", StaffAccount.class)
				.getResultList();
		model.addAttribute("staffAccounts", staffAccounts);
		return "staff-accounts";
	}

	@GetMapping("/staff-roles")
	public String staffRoles(Model model) {
		List<StaffRole> staffRoles = entityManager.createQuery("from StaffRole", StaffRole.class).getResultList();
		List<StaffAccount> staffAccounts = entityManager.createQuery("from StaffAccount", StaffAccount.class)
				.getResultList();
		List<Role> roles = entityManager.createQuery("from Role", Role.class).getResultList();
		model.addAttribute("staffRoles", staffRoles);
		model.addAttribute("staffAccounts", staffAccounts);
		model.addAttribute("roles", roles);
		return "staff-roles";
	}

	@GetMapping("/suppliers")
	public String suppliers(Model model) {
		List<Supplier> suppliers = entityManager.createQuery("from Supplier", Supplier.class).getResultList();
		model.addAttribute("suppliers", suppliers);
		return "suppliers";
	}

	@GetMapping("/tags")
	public String tags(Model model) {
		List<Tag> tags = entityManager.createQuery("from Tag", Tag.class).getResultList();
		model.addAttribute("tags", tags);
		return "tags";
	}
}
