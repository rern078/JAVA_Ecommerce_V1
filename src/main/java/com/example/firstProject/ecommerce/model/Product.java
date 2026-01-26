package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "product_name")
	private String name;

	@Column(length = 2000, name = "product_description")
	private String description;

	@Column(nullable = false, precision = 12, scale = 2, name = "unit_price")
	private BigDecimal price;

	@Column(nullable = false, name = "units_in_stock")
	private int stock;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "sku")
	private String sku;

	@Column(name = "idsku")
	private String idSku;

	@Column(name = "vendor_product_id")
	private String vendorProductId;

	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;

	@Column(precision = 12, scale = 2)
	private BigDecimal msrp;

	@Column(name = "available_size")
	private String availableSize;

	@Column(name = "available_colors")
	private String availableColors;

	private String size;

	private String color;

	@Column(precision = 8, scale = 2)
	private BigDecimal discount;

	@Column(precision = 8, scale = 2, name = "unit_weight")
	private BigDecimal unitWeight;

	@Column(name = "units_on_order")
	private Integer unitsOnOrder;

	@Column(name = "reorder_level")
	private Integer reorderLevel;

	@Column(name = "product_available")
	private Boolean productAvailable;

	@Column(name = "discount_available")
	private Boolean discountAvailable;

	@Column(name = "current_order")
	private Boolean currentOrder;

	@Column(name = "picture")
	private String picture;

	@Column(name = "product_gallery", columnDefinition = "TEXT")
	private String productGallery;

	private Integer ranking;

	@Column(length = 2000)
	private String note;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	public Product() {
	}

	public Product(String name, String description, BigDecimal price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	@PrePersist
	void onCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getIdSku() {
		return idSku;
	}

	public void setIdSku(String idSku) {
		this.idSku = idSku;
	}

	public String getVendorProductId() {
		return vendorProductId;
	}

	public void setVendorProductId(String vendorProductId) {
		this.vendorProductId = vendorProductId;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public String getAvailableSize() {
		return availableSize;
	}

	public void setAvailableSize(String availableSize) {
		this.availableSize = availableSize;
	}

	public String getAvailableColors() {
		return availableColors;
	}

	public void setAvailableColors(String availableColors) {
		this.availableColors = availableColors;
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getUnitWeight() {
		return unitWeight;
	}

	public void setUnitWeight(BigDecimal unitWeight) {
		this.unitWeight = unitWeight;
	}

	public Integer getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(Integer unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Boolean getProductAvailable() {
		return productAvailable;
	}

	public void setProductAvailable(Boolean productAvailable) {
		this.productAvailable = productAvailable;
	}

	public Boolean getDiscountAvailable() {
		return discountAvailable;
	}

	public void setDiscountAvailable(Boolean discountAvailable) {
		this.discountAvailable = discountAvailable;
	}

	public Boolean getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Boolean currentOrder) {
		this.currentOrder = currentOrder;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getProductGallery() {
		return productGallery;
	}

	public void setProductGallery(String productGallery) {
		this.productGallery = productGallery;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
