package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.dto.CreateProductRequest;
import com.example.firstProject.ecommerce.dto.UpdateProductRequest;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Product;
import com.example.firstProject.ecommerce.model.Category;
import com.example.firstProject.ecommerce.model.Supplier;
import com.example.firstProject.ecommerce.repository.CategoryRepository;
import com.example.firstProject.ecommerce.repository.ProductRepository;
import com.example.firstProject.ecommerce.repository.SupplierRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
	private final ProductRepository productRepository;
	private final SupplierRepository supplierRepository;
	private final CategoryRepository categoryRepository;

	public CatalogService(ProductRepository productRepository,
						  SupplierRepository supplierRepository,
						  CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product not found: " + id));
	}

	public Product createProduct(CreateProductRequest request) {
		Product product = new Product(request.name(), request.description(), request.price(), request.stock());
		applyDetails(product, request.supplierId(), request.categoryId(), request.sku(), request.idSku(),
				request.vendorProductId(), request.quantityPerUnit(), request.msrp(), request.availableSize(),
				request.availableColors(), request.size(), request.color(), request.discount(),
				request.unitWeight(), request.unitsOnOrder(), request.reorderLevel(), request.productAvailable(),
				request.discountAvailable(), request.currentOrder(), request.picture(), request.productGallery(),
				request.ranking(), request.note());
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, UpdateProductRequest request) {
		Product product = getProduct(id);
		product.setName(request.name());
		product.setDescription(request.description());
		product.setPrice(request.price());
		product.setStock(request.stock());
		applyDetails(product, request.supplierId(), request.categoryId(), request.sku(), request.idSku(),
				request.vendorProductId(), request.quantityPerUnit(), request.msrp(), request.availableSize(),
				request.availableColors(), request.size(), request.color(), request.discount(),
				request.unitWeight(), request.unitsOnOrder(), request.reorderLevel(), request.productAvailable(),
				request.discountAvailable(), request.currentOrder(), request.picture(), request.productGallery(),
				request.ranking(), request.note());
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		Product product = getProduct(id);
		productRepository.delete(product);
	}

	public Product appendProductGallery(Long id, List<String> urls) {
		Product product = getProduct(id);
		if (urls == null || urls.isEmpty()) {
			return product;
		}
		String existing = product.getProductGallery();
		String merged = mergeGallery(existing, urls);
		product.setProductGallery(merged);
		return productRepository.save(product);
	}

	private void applyDetails(Product product,
							  Long supplierId,
							  Long categoryId,
							  String sku,
							  String idSku,
							  String vendorProductId,
							  String quantityPerUnit,
							  java.math.BigDecimal msrp,
							  String availableSize,
							  String availableColors,
							  String size,
							  String color,
							  java.math.BigDecimal discount,
							  java.math.BigDecimal unitWeight,
							  Integer unitsOnOrder,
							  Integer reorderLevel,
							  Boolean productAvailable,
							  Boolean discountAvailable,
							  Boolean currentOrder,
							  String picture,
							  String productGallery,
							  Integer ranking,
							  String note) {
		Supplier supplier = supplierId == null ? null : supplierRepository.findById(supplierId).orElse(null);
		Category category = categoryId == null ? null : categoryRepository.findById(categoryId).orElse(null);
		product.setSupplier(supplier);
		product.setCategory(category);
		product.setSku(sku);
		product.setIdSku(idSku);
		product.setVendorProductId(vendorProductId);
		product.setQuantityPerUnit(quantityPerUnit);
		product.setMsrp(msrp);
		product.setAvailableSize(availableSize);
		product.setAvailableColors(availableColors);
		product.setSize(size);
		product.setColor(color);
		product.setDiscount(discount);
		product.setUnitWeight(unitWeight);
		product.setUnitsOnOrder(unitsOnOrder);
		product.setReorderLevel(reorderLevel);
		product.setProductAvailable(productAvailable);
		product.setDiscountAvailable(discountAvailable);
		product.setCurrentOrder(currentOrder);
		product.setPicture(picture);
		product.setProductGallery(productGallery);
		product.setRanking(ranking);
		product.setNote(note);
	}

	private String mergeGallery(String existing, List<String> urls) {
		StringBuilder builder = new StringBuilder();
		if (existing != null && !existing.isBlank()) {
			builder.append(existing.trim());
		}
		for (String url : urls) {
			if (url == null || url.isBlank()) {
				continue;
			}
			if (builder.length() > 0) {
				builder.append(",");
			}
			builder.append(url.trim());
		}
		return builder.length() == 0 ? null : builder.toString();
	}
}
