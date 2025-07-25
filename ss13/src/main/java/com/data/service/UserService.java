package com.data.service;

import com.data.model.entity.User;
import com.data.model.dto.request.UserLogin;
import com.data.model.dto.request.UserRegister;
import com.data.model.dto.response.JWTResponse;

public interface UserService{
    User registerUser(UserRegister userRegister);

    JWTResponse login(UserLogin userLogin);

    User editUser(User user);

    User getCurrentUser();
}
