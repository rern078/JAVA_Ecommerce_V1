package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "variant_attribute_values")
public class VariantAttributeValue {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@Column(name = "variant_attribute_value_id")
	private UUID variantAttributeValueId;

	@ManyToOne
	@JoinColumn(name = "attribute_value_id")
	private AttributeValue attributeValue;

	public UUID getId() {
		return id;
	}

	public UUID getVariantAttributeValueId() {
		return variantAttributeValueId;
	}

	public void setVariantAttributeValueId(UUID variantAttributeValueId) {
		this.variantAttributeValueId = variantAttributeValueId;
	}

	public AttributeValue getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(AttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}
}
