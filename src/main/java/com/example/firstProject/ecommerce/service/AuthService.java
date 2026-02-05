package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.exception.BadRequestException;
import com.example.firstProject.ecommerce.model.AppUser;
import com.example.firstProject.ecommerce.model.UserRole;
import com.example.firstProject.ecommerce.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void register(String email, String password, String name) {
		if (email == null || email.isBlank()) {
			throw new BadRequestException("Email is required.");
		}
		if (name == null || name.isBlank()) {
			throw new BadRequestException("Name is required.");
		}
		if (password == null || password.isBlank()) {
			throw new BadRequestException("Password is required.");
		}
		String normalizedEmail = email.toLowerCase();
		if (userRepository.existsByEmail(normalizedEmail)) {
			throw new BadRequestException("Email already registered.");
		}
		String hash = passwordEncoder.encode(password);
		userRepository.save(new AppUser(name, normalizedEmail, hash, UserRole.USER));
	}

	public boolean authenticate(String email, String password) {
		if (email == null || password == null) {
			return false;
		}
		return userRepository.findByEmail(email.toLowerCase())
				.map(user -> passwordEncoder.matches(password, user.getPasswordHash()))
				.orElse(false);
	}

	public Optional<String> getDisplayName(String email) {
		if (email == null) {
			return Optional.empty();
		}
		return userRepository.findByEmail(email.toLowerCase()).map(AppUser::getName);
	}

	public Optional<UserRole> getUserRole(String email) {
		if (email == null) {
			return Optional.empty();
		}
		return userRepository.findByEmail(email.toLowerCase()).map(AppUser::getRole);
	}
}
