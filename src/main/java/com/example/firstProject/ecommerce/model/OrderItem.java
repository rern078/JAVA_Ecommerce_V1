package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false, precision = 12, scale = 2, name = "price")
	private BigDecimal unitPrice;

	@Column(nullable = false, precision = 12, scale = 2, name = "total")
	private BigDecimal lineTotal;

	@Column(precision = 8, scale = 2)
	private BigDecimal discount;

	@Column(name = "idsku")
	private String idSku;

	private String size;

	private String color;

	private Boolean fulfilled;

	@Column(name = "ship_date")
	private LocalDateTime shipDate;

	@Column(name = "bill_date")
	private LocalDateTime billDate;

	public OrderItem() {
	}

	public OrderItem(Order order, Product product, int quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.lineTotal = lineTotal;
	}

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getIdSku() {
		return idSku;
	}

	public void setIdSku(String idSku) {
		this.idSku = idSku;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(Boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public LocalDateTime getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDateTime shipDate) {
		this.shipDate = shipDate;
	}

	public LocalDateTime getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
}
