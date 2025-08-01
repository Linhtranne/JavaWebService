package ra.edu.model.dto.response;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {
    private String field;
    private String message;
}