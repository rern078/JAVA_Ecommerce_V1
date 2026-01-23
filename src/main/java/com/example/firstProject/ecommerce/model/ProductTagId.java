package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductTagId implements Serializable {
	@Column(name = "tag_id")
	private Integer tagId;

	@Column(name = "product_id")
	private Long productId;

	public ProductTagId() {
	}

	public ProductTagId(Integer tagId, Long productId) {
		this.tagId = tagId;
		this.productId = productId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
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
		ProductTagId that = (ProductTagId) o;
		return Objects.equals(tagId, that.tagId) && Objects.equals(productId, that.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagId, productId);
	}
}
