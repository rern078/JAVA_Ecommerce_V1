package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCouponId implements Serializable {
	@Column(name = "coupon_id")
	private Integer couponId;

	@Column(name = "product_id")
	private Long productId;

	public ProductCouponId() {
	}

	public ProductCouponId(Integer couponId, Long productId) {
		this.couponId = couponId;
		this.productId = productId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductCouponId that = (ProductCouponId) o;
		return Objects.equals(couponId, that.couponId) && Objects.equals(productId, that.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(couponId, productId);
	}
}
