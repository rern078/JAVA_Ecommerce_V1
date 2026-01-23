package com.example.firstProject.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_coupons")
public class ProductCoupon {
	@EmbeddedId
	private ProductCouponId id = new ProductCouponId();

	@ManyToOne
	@MapsId("couponId")
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductCouponId getId() {
		return id;
	}

	public void setId(ProductCouponId id) {
		this.id = id;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
		if (coupon != null) {
			this.id.setCouponId(coupon.getId());
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
