package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "first_name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "class")
	private String customerClass;

	private String room;

	private String building;

	@Column(name = "address1", length = 2000)
	private String address1;

	@Column(name = "address2", length = 2000)
	private String address2;

	private String city;

	private String state;

	@Column(name = "postal_code")
	private String postalCode;

	private String country;

	private String phone;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "voice_mail")
	private String voiceMail;

	@Column(name = "password")
	private String password;

	@Column(name = "credit_card")
	private String creditCard;

	@Column(name = "credit_card_type_id")
	private String creditCardTypeId;

	@Column(name = "card_exp_mo")
	private String cardExpMo;

	@Column(name = "card_exp_yr")
	private String cardExpYr;

	@Column(name = "billing_address", length = 2000)
	private String billingAddress;

	@Column(name = "billing_city")
	private String billingCity;

	@Column(name = "billing_region")
	private String billingRegion;

	@Column(name = "billing_postal_code")
	private String billingPostalCode;

	@Column(name = "billing_country")
	private String billingCountry;

	@Column(name = "ship_address", length = 2000)
	private String shipAddress;

	@Column(name = "ship_city")
	private String shipCity;

	@Column(name = "ship_region")
	private String shipRegion;

	@Column(name = "ship_postal_code")
	private String shipPostalCode;

	@Column(name = "ship_country")
	private String shipCountry;

	@Column(length = 2000)
	private String address;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	public Customer() {
	}

	public Customer(String name, String email, String address) {
		this.name = name;
		this.email = email;
		this.address = address;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVoiceMail() {
		return voiceMail;
	}

	public void setVoiceMail(String voiceMail) {
		this.voiceMail = voiceMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreditCardTypeId() {
		return creditCardTypeId;
	}

	public void setCreditCardTypeId(String creditCardTypeId) {
		this.creditCardTypeId = creditCardTypeId;
	}

	public String getCardExpMo() {
		return cardExpMo;
	}

	public void setCardExpMo(String cardExpMo) {
		this.cardExpMo = cardExpMo;
	}

	public String getCardExpYr() {
		return cardExpYr;
	}

	public void setCardExpYr(String cardExpYr) {
		this.cardExpYr = cardExpYr;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingRegion() {
		return billingRegion;
	}

	public void setBillingRegion(String billingRegion) {
		this.billingRegion = billingRegion;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipRegion() {
		return shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public String getShipPostalCode() {
		return shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
