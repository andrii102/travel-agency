package com.epam.finaltask.dto;

import lombok.Data;

@Data
public class UserTableDTO {
    private String id;
    private String username;
    private String role;
    private boolean blocked;
}
