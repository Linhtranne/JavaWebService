package ra.edu.model.dto.response;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String token;
    private String username;
    private String role;
}