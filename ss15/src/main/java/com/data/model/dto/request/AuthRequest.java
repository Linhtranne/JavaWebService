package com.data.model.dto.request;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

    public String getUserName() {
        return username;
    }
}
