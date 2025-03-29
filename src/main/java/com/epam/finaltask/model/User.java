package com.epam.finaltask.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Setter
@Getter
@Table(name = "\"user\"")
@Entity
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
    private UUID id;

    private String username;

    private String password;

	@Enumerated(EnumType.STRING)
    private Role role;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> vouchers;

    private String phoneNumber;

    private Double balance;

    private boolean active;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
	}

	public void addVoucher(Voucher voucher) {
		vouchers.add(voucher);
	}
}