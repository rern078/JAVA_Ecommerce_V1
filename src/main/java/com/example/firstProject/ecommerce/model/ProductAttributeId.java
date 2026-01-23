package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ProductAttributeId implements Serializable {
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "attribute_id")
	private UUID attributeId;

	public ProductAttributeId() {
	}

	public ProductAttributeId(Long productId, UUID attributeId) {
		this.productId = productId;
		this.attributeId = attributeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public UUID getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(UUID attributeId) {
		this.attributeId = attributeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductAttributeId that = (ProductAttributeId) o;
		return Objects.equals(productId, that.productId) && Objects.equals(attributeId, that.attributeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, attributeId);
	}
}
