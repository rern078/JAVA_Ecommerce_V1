package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.dto.CreateUserRequest;
import com.example.firstProject.ecommerce.dto.UpdateUserRequest;
import com.example.firstProject.ecommerce.exception.BadRequestException;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.AppUser;
import com.example.firstProject.ecommerce.repository.UserRepository;
import com.example.firstProject.ecommerce.model.UserRole;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<AppUser> listUsers() {
		return userRepository.findAll();
	}

	public AppUser getUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}

	public AppUser createUser(CreateUserRequest request) {
		validateEmail(request.email(), null);
		String normalizedEmail = request.email().toLowerCase();
		String hash = passwordEncoder.encode(request.password());
		UserRole role = request.role() != null ? request.role() : UserRole.USER;
		AppUser user = new AppUser(request.name().trim(), normalizedEmail, hash, role);
		return userRepository.save(user);
	}

	public AppUser updateUser(Long id, UpdateUserRequest request) {
		AppUser user = getUser(id);
		validateEmail(request.email(), id);

		user.setName(request.name().trim());
		user.setEmail(request.email().toLowerCase());
		if (request.role() != null) {
			user.setRole(request.role());
		}

		if (request.password() != null && !request.password().isBlank()) {
			user.setPasswordHash(passwordEncoder.encode(request.password()));
		}

		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		AppUser user = getUser(id);
		userRepository.delete(user);
	}

	private void validateEmail(String email, Long existingId) {
		if (email == null || email.isBlank()) {
			throw new BadRequestException("Email is required.");
		}
		String normalizedEmail = email.toLowerCase();
		if (existingId == null) {
			if (userRepository.existsByEmail(normalizedEmail)) {
				throw new BadRequestException("Email already registered.");
			}
		} else {
			userRepository.findByEmail(normalizedEmail).ifPresent(found -> {
				if (!found.getId().equals(existingId)) {
					throw new BadRequestException("Email already registered.");
				}
			});
		}
	}
}
