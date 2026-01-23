package com.example.firstProject.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class StaffRoleId implements Serializable {
	@Column(name = "staff_id")
	private UUID staffId;

	@Column(name = "role_id")
	private Integer roleId;

	public StaffRoleId() {
	}

	public StaffRoleId(UUID staffId, Integer roleId) {
		this.staffId = staffId;
		this.roleId = roleId;
	}

	public UUID getStaffId() {
		return staffId;
	}

	public void setStaffId(UUID staffId) {
		this.staffId = staffId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StaffRoleId that = (StaffRoleId) o;
		return Objects.equals(staffId, that.staffId) && Objects.equals(roleId, that.roleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(staffId, roleId);
	}
}
