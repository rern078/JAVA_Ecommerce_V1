package com.example.firstProject.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record StaffRoleRequest(
		@NotNull UUID staffId,
		@NotNull Integer roleId
) {
}
