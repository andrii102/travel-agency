package com.epam.finaltask.dto;

import com.epam.finaltask.model.Voucher;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private String id;

	@NotBlank(message = "Username is required")
	@Size(min = 4, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	private String firstName;

	private String lastName;

	@NotBlank(message = "Role is required")
	private String role = "USER";

	private List<Voucher> vouchers;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number format")
	private String phoneNumber;

	private String email;

	@DecimalMin(value = "0.0", message = "Balance cannot be negative")
	private Double balance;

	private boolean active;

	public String getRole() {
		return role != null ? role : "USER";  // Default role
	}

	public void setRole(String role) {
		this.role = (role == null || role.isBlank()) ? "USER" : role;
	}
}
