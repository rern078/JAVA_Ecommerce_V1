package com.example.firstProject.ecommerce.repository;

import com.example.firstProject.ecommerce.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	boolean existsByEmail(String email);

	Optional<AppUser> findByEmail(String email);
}
