package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "card_items")
public class CardItem {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Short quantity;

	public UUID getId() {
		return id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
}
