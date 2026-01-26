package com.example.firstProject.ecommerce.repository;

import com.example.firstProject.ecommerce.model.Gallery;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, UUID> {
	List<Gallery> findByProductIdOrderByDisplayOrderAsc(Long productId);

	long countByProductId(Long productId);
}
