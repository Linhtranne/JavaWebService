package com.data.model.dto.request;


import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String password;
    private String fullName;
    private String address;
    private String email;
    private String phone;
}