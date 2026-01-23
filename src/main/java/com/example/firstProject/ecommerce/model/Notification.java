package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "notifications")
public class Notification {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private StaffAccount account;

	private String title;

	private String content;

	private Boolean seen;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "receive_time")
	private LocalTime receiveTime;

	@Column(name = "notification_expiry_date")
	private LocalDate notificationExpiryDate;

	public UUID getId() {
		return id;
	}

	public StaffAccount getAccount() {
		return account;
	}

	public void setAccount(StaffAccount account) {
		this.account = account;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(LocalTime receiveTime) {
		this.receiveTime = receiveTime;
	}

	public LocalDate getNotificationExpiryDate() {
		return notificationExpiryDate;
	}

	public void setNotificationExpiryDate(LocalDate notificationExpiryDate) {
		this.notificationExpiryDate = notificationExpiryDate;
	}
}
