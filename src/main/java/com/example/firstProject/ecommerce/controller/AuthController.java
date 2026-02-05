package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.model.UserRole;
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
		String email = (String) session.getAttribute("userEmail");
		if (email != null) {
			return redirectByRole(session, email);
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
		return redirectByRole(session, email);
	}

	@GetMapping("/register")
	public String register(HttpSession session) {
		String email = (String) session.getAttribute("userEmail");
		if (email != null) {
			return redirectByRole(session, email);
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
		return redirectByRole(session, email);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	private String redirectByRole(HttpSession session, String email) {
		String normalizedEmail = email == null ? null : email.toLowerCase();
		if (normalizedEmail == null) {
			return "redirect:/login";
		}
		UserRole role = resolveRole(session, normalizedEmail);
		session.setAttribute("userEmail", normalizedEmail);
		session.setAttribute("userRole", role.name());
		if (role == UserRole.ADMIN) {
			return "redirect:/dashboard";
		}
		return "redirect:/store";
	}

	private UserRole resolveRole(HttpSession session, String email) {
		String sessionEmail = (String) session.getAttribute("userEmail");
		Object roleAttr = session.getAttribute("userRole");
		if (email != null && email.equalsIgnoreCase(sessionEmail) && roleAttr instanceof String roleName) {
			try {
				return UserRole.valueOf(roleName);
			} catch (IllegalArgumentException ignored) {
			}
		}
		return authService.getUserRole(email).orElse(UserRole.USER);
	}
}
