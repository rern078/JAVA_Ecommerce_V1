package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/login")
	public String login(HttpSession session) {
		if (session.getAttribute("userEmail") != null) {
			return "redirect:/view";
		}
		return "login";
	}

	@PostMapping("/login")
	public String doLogin(@RequestParam String email,
						  @RequestParam String password,
						  HttpSession session,
						  Model model) {
		if (!authService.authenticate(email, password)) {
			model.addAttribute("error", "Invalid email or password.");
			return "login";
		}
		session.setAttribute("userEmail", email);
		return "redirect:/view";
	}

	@GetMapping("/register")
	public String register(HttpSession session) {
		if (session.getAttribute("userEmail") != null) {
			return "redirect:/view";
		}
		return "register";
	}

	@PostMapping("/register")
	public String doRegister(@RequestParam String name,
							 @RequestParam String email,
							 @RequestParam String password,
							 HttpSession session,
							 Model model) {
		try {
			authService.register(email, password, name);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			return "register";
		}
		session.setAttribute("userEmail", email);
		return "redirect:/view";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
