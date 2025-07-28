package com.data.ss14.service;

import com.data.ss14.model.dto.request.UserRegister;
import com.data.ss14.model.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRegister userRegister);
    UserResponseDTO getCurrentUser();
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
}
