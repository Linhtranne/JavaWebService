package com.data.model.mapper;

import com.data.model.dto.response.UserResponseDTO;
import com.data.model.entity.Role;
import com.data.model.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.isStatus(),
                roleNames
        );
    }
}
