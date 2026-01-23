package com.example.firstProject.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
	@EmbeddedId
	private ProductCategoryId id = new ProductCategoryId();

	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductCategoryId getId() {
		return id;
	}

	public void setId(ProductCategoryId id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			this.id.setCategoryId(category.getId());
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		if (product != null) {
			this.id.setProductId(product.getId());
		}
	}
}
