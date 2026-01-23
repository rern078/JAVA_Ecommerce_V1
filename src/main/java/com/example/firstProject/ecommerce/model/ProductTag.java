package com.example.firstProject.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_tags")
public class ProductTag {
	@EmbeddedId
	private ProductTagId id = new ProductTagId();

	@ManyToOne
	@MapsId("tagId")
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductTagId getId() {
		return id;
	}

	public void setId(ProductTagId id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
		if (tag != null) {
			this.id.setTagId(tag.getId());
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
