package com.data.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO fromEntity(com.data.model.entity.Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
