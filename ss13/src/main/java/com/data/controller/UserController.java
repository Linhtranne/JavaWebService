package com.data.controller;

import com.data.model.dto.request.UserLogin;
import com.data.model.dto.request.UserRegister;
import com.data.model.dto.response.JWTResponse;
import com.data.model.dto.request.UserDTO;
import com.data.model.entity.User;
import com.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegister userRegister) {
        User user = userService.registerUser(userRegister);
        return ResponseEntity.ok(UserDTO.fromEntity(user));
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody UserLogin userLogin) {
        JWTResponse jwtResponse = userService.login(userLogin);
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(UserDTO.fromEntity(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = User.builder()
                .id(java.util.UUID.fromString(id))
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .status(userDTO.isStatus())
                .build();
        User updatedUser = userService.editUser(user);
        return ResponseEntity.ok(UserDTO.fromEntity(updatedUser));
    }
}