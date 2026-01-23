package com.example.firstProject.ecommerce.repository;

import com.example.firstProject.ecommerce.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerId(Long customerId);

	@EntityGraph(attributePaths = {"customer", "items", "items.product"})
	List<Order> findAll();

	@EntityGraph(attributePaths = {"customer", "items", "items.product"})
	List<Order> findWithDetailsByCustomerId(Long customerId);

	@EntityGraph(attributePaths = {"customer", "items", "items.product"})
	java.util.Optional<Order> findWithDetailsById(Long id);
}
