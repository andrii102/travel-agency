package com.epam.finaltask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Table(name = "\"user\"")
@Entity
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
    private UUID id;

	@NotBlank(message = "Username is required")
	@Size(min=4, max=20, message = "Username must be 4-20 characters")
    private String username;

	@NotBlank(message = "Password is required")
	@Size(min=8, message = "Password must be at least 8 characters")
    private String password;


	private String firstName;

	private String lastName;

	@Enumerated(EnumType.STRING)
    private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> vouchers;

	@Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number format")
    private String phoneNumber;

	private String email;

	private LocalDate registrationDate;

	@DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private Double balance;

    private boolean accountStatus;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
	}

	@PrePersist
	public void setRegistrationDate() {
		registrationDate = LocalDate.now();
	}

	public void addVoucher(Voucher voucher) {
		vouchers.add(voucher);
	}

	public void setActive(boolean b) {
		accountStatus = b;
	}
}