package com.data.service;

import com.data.model.dto.request.AuthRequest;
import com.data.model.dto.response.AuthResponse;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.entity.Role;
import com.data.model.entity.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import com.data.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        Optional<Role> userRole = roleRepository.findByRoleName("USER");
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setEnabled(true);
        user.setRoles(List.of(userRole));
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUserName(request.getUserName());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken((UserDetails) user);
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}