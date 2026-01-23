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
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID cardId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public UUID getCardId() {
		return cardId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
