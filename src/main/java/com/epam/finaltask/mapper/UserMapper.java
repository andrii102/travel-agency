package com.epam.finaltask.mapper;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.dto.UserTableDTO;
import com.epam.finaltask.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);

    default UserTableDTO toUserTableDTO(User user) {
        if (user == null) return null;
        UserTableDTO dto = new UserTableDTO();
        dto.setId(String.valueOf(user.getId()));
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        dto.setBlocked(user.isAccountStatus());
        return dto;
    }
}
