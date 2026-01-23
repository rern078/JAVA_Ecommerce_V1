package com.example.firstProject.ecommerce.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_roles")
public class StaffRole {
	@EmbeddedId
	private StaffRoleId id = new StaffRoleId();

	@ManyToOne
	@MapsId("staffId")
	@JoinColumn(name = "staff_id")
	private StaffAccount staff;

	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	private Role role;

	public StaffRoleId getId() {
		return id;
	}

	public void setId(StaffRoleId id) {
		this.id = id;
	}

	public StaffAccount getStaff() {
		return staff;
	}

	public void setStaff(StaffAccount staff) {
		this.staff = staff;
		if (staff != null) {
			this.id.setStaffId(staff.getId());
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		if (role != null) {
			this.id.setRoleId(role.getId());
		}
	}
}
