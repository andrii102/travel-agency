package com.epam.finaltask.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.epam.finaltask.model.Permission.*;

public enum Role {
    ADMIN(
            "Full system access",
            Set.of(Permission.values()) // All permissions
    ),
    MANAGER(
            "Manage user content",
            Set.of(
                    MANAGER_UPDATE,
                    USER_READ,
                    USER_UPDATE
            )
    ),
    USER(
            "Standard user privileges",
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    );

    private final String description;
    private final Set<Permission> permissions;

    Role(String description, Set<Permission> permissions) {
        this.description = description;
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Add role authority (ROLE_ prefix required by Spring)
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        // Add individual permissions
        permissions.forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name()))
        );

        return authorities;
    }
}