package ra.edu.mapper;


import ra.edu.model.dto.request.UserDTO;
import ra.edu.model.entity.User;

import java.util.Set;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .isActive(user.getIsActive())
                .build();
    }
    public static User toEntity(UserDTO DTO) {
        return User.builder()
                .userId(DTO.getUserId())
                .username(DTO.getUsername())
                .passwordHash(DTO.getPasswordHash())
                .email(DTO.getEmail())
                .fullName(DTO.getFullName())
                .role(User.Role.valueOf(DTO.getRole()))
                .isActive(DTO.getIsActive())
                .build();
    }
}