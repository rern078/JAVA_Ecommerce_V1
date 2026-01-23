package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductShippingId implements Serializable {
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "shipping_id")
	private Integer shippingId;

	public ProductShippingId() {
	}

	public ProductShippingId(Long productId, Integer shippingId) {
		this.productId = productId;
		this.shippingId = shippingId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductShippingId that = (ProductShippingId) o;
		return Objects.equals(productId, that.productId) && Objects.equals(shippingId, that.shippingId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, shippingId);
	}
}
