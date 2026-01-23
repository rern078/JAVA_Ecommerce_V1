package com.example.firstProject.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
	@EmbeddedId
	private ProductAttributeId id = new ProductAttributeId();

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@MapsId("attributeId")
	@JoinColumn(name = "attribute_id")
	private Attribute attribute;

	public ProductAttributeId getId() {
		return id;
	}

	public void setId(ProductAttributeId id) {
		this.id = id;
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

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
		if (attribute != null) {
			this.id.setAttributeId(attribute.getId());
		}
	}
}
