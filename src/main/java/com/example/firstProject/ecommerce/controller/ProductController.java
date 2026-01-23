package com.example.firstProject.ecommerce.controller;

import com.example.firstProject.ecommerce.dto.CreateProductRequest;
import com.example.firstProject.ecommerce.dto.ProductResponse;
import com.example.firstProject.ecommerce.dto.UpdateProductRequest;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.service.CatalogService;
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
@RequestMapping("/api/products")
public class ProductController {
	private final CatalogService catalogService;

	public ProductController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping
	public List<ProductResponse> listProducts() {
		return catalogService.listProducts().stream()
				.map(ProductController::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public ProductResponse getProduct(@PathVariable Long id) {
		return toResponse(catalogService.getProduct(id));
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
		Product created = catalogService.createProduct(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
	}

	@PutMapping("/{id}")
	public ProductResponse updateProduct(@PathVariable Long id,
										 @Valid @RequestBody UpdateProductRequest request) {
		return toResponse(catalogService.updateProduct(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		catalogService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	private static ProductResponse toResponse(Product product) {
		return new ProductResponse(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getStock(),
				product.getSupplier() != null ? product.getSupplier().getId() : null,
				product.getCategory() != null ? product.getCategory().getId() : null,
				product.getSku(),
				product.getIdSku(),
				product.getVendorProductId(),
				product.getQuantityPerUnit(),
				product.getMsrp(),
				product.getAvailableSize(),
				product.getAvailableColors(),
				product.getSize(),
				product.getColor(),
				product.getDiscount(),
				product.getUnitWeight(),
				product.getUnitsOnOrder(),
				product.getReorderLevel(),
				product.getProductAvailable(),
				product.getDiscountAvailable(),
				product.getCurrentOrder(),
				product.getPicture(),
				product.getRanking(),
				product.getNote(),
				product.getCreatedAt()
		);
	}
}
