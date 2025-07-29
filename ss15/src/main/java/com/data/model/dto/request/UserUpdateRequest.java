package com.data.model.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String address;
    private String email;
    private String phone;
}