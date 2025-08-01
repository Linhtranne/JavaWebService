package ra.edu.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.edu.util.JWTUtil;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JWTProvider {
    @Autowired
    private JWTUtil jwtUtil;

    public String generateToken(UserDetails userDetails, String role) {
        return jwtUtil.generateToken(userDetails.getUsername(), role);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtUtil.isTokenValid(token, userDetails.getUsername());
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    public String getRoleFromToken(String token) {
        return jwtUtil.extractRole(token);
    }
}