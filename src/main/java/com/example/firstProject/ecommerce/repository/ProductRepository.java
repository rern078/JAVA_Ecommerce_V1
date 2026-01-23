package com.example.firstProject.ecommerce.repository;

import com.example.firstProject.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
