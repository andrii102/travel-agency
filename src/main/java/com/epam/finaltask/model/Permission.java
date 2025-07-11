package com.epam.finaltask.model;

public enum Permission {
	ADMIN_READ,
    ADMIN_UPDATE,
    ADMIN_CREATE,
    ADMIN_DELETE,
    MANAGER_READ,
    MANAGER_UPDATE,
    MANAGER_CREATE,
    MANAGER_DELETE,
    USER_READ,
    USER_UPDATE,
    USER_CREATE,
    USER_DELETE;

    private String permission;
}
