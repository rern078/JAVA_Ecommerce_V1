package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.CreateUserRequest;
import com.example.firstProject.ecommerce.dto.UpdateUserRequest;
import com.example.firstProject.ecommerce.dto.UserResponse;
import com.example.firstProject.ecommerce.model.AppUser;
import com.example.firstProject.ecommerce.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> listUsers() {
		return userService.listUsers().stream()
				.map(UserController::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		return toResponse(userService.getUser(id));
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
		AppUser created = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	@PutMapping("/{id}")
	public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
		return toResponse(userService.updateUser(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	private static UserResponse toResponse(AppUser user) {
		return new UserResponse(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getRole(),
				user.getCreatedAt()
		);
	}
}
