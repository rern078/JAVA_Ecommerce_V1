package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product_shippings")
public class ProductShipping {
	@EmbeddedId
	private ProductShippingId id = new ProductShippingId();

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@MapsId("shippingId")
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	@Column(name = "ship_charge")
	private BigDecimal shipCharge;

	private Boolean free;

	@Column(name = "estimated_days")
	private BigDecimal estimatedDays;

	public ProductShippingId getId() {
		return id;
	}

	public void setId(ProductShippingId id) {
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

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
		if (shipping != null) {
			this.id.setShippingId(shipping.getId());
		}
	}

	public BigDecimal getShipCharge() {
		return shipCharge;
	}

	public void setShipCharge(BigDecimal shipCharge) {
		this.shipCharge = shipCharge;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	public BigDecimal getEstimatedDays() {
		return estimatedDays;
	}

	public void setEstimatedDays(BigDecimal estimatedDays) {
		this.estimatedDays = estimatedDays;
	}
}
