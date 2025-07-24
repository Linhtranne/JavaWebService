package com.data.controller;

import com.data.model.dto.reqest.ApiResponse;
import com.data.model.dto.reqest.UserRequestDTO;
import com.data.model.dto.response.UserResponseDTO;
import com.data.model.entity.User;
import com.data.model.mapper.UserMapper;
import com.data.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody UserRequestDTO dto) {
        User user = userService.register(dto.getUsername(), dto.getPassword());
        UserResponseDTO responseDTO = UserMapper.toResponseDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(responseDTO, "Đăng ký thành công", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDTO>> login(@Valid @RequestBody UserRequestDTO dto, HttpSession session) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        session.setAttribute("username", user.getUsername());
        UserResponseDTO responseDTO = UserMapper.toResponseDTO(user);
        return ResponseEntity.ok(new ApiResponse<>(responseDTO, "Đăng nhập thành công", 200));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new ApiResponse<>(null, "Đăng xuất thành công", 200));
    }
}
