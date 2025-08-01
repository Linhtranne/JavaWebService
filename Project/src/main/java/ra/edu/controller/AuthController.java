package ra.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiResponse;
import ra.edu.model.dto.response.JWTResponse;
import ra.edu.model.entity.User;
import ra.edu.repository.UserRepository;
import ra.edu.security.jwt.JWTProvider;
import ra.edu.security.principal.UserDetailsServiceImpl;
import ra.edu.model.dto.request.RegisterRequest;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RegisterRequest registerRequest;


    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request){
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ApiResponse.<Void>builder()
                    .success(false)
                    .message("Username already exists")
                    .data(null)
                    .errors(null)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Set.of(request.getRole()))
                .build();
        userRepository.save(user);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Registration successful")
                .data(null)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<JWTResponse> login(@RequestBody RegisterRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        String token = jwtProvider.generateToken(userDetails, role);
        JWTResponse jwtResponse = JWTResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .role(role)
                .build();
        return ApiResponse.<JWTResponse>builder()
                .success(true)
                .message("Login successful")
                .data(jwtResponse)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Logout successful")
                .data(null)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}