package com.example.firstProject.ecommerce.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "order_number")
	private String orderNumber;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Column(name = "ship_date")
	private LocalDateTime shipDate;

	@Column(name = "required_date")
	private LocalDateTime requiredDate;

	@ManyToOne
	@JoinColumn(name = "shipper_id")
	private Shipper shipper;

	@Column(precision = 12, scale = 2)
	private BigDecimal freight;

	@Column(precision = 12, scale = 2, name = "sales_tax")
	private BigDecimal salesTax;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@Column(name = "transact_status")
	private String transactStatus;

	@Column(name = "err_loc")
	private String errLoc;

	@Column(name = "err_msg", length = 2000)
	private String errMsg;

	private Boolean fulfilled;

	private Boolean deleted;

	private Boolean paid;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status = OrderStatus.NEW;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal total = BigDecimal.ZERO;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	public Order() {
	}

	public Order(Customer customer) {
		this.customer = customer;
	}

	@PrePersist
	void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		if (createdAt == null) {
			createdAt = now;
		}
		if (orderDate == null) {
			orderDate = now;
		}
	}

	public Long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDateTime shipDate) {
		this.shipDate = shipDate;
	}

	public LocalDateTime getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(LocalDateTime requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactStatus() {
		return transactStatus;
	}

	public void setTransactStatus(String transactStatus) {
		this.transactStatus = transactStatus;
	}

	public String getErrLoc() {
		return errLoc;
	}

	public void setErrLoc(String errLoc) {
		this.errLoc = errLoc;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Boolean getFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(Boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items.clear();
		if (items != null) {
			this.items.addAll(items);
		}
	}
}
