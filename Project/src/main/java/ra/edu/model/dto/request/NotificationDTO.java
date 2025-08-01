package ra.edu.model.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private Integer notificationId;

    @NotNull
    private Integer userId;

    @NotBlank
    private String message;

    @Size(max = 50)
    private String type;

    @Size(max = 500)
    private String targetUrl;

    @NotNull
    private Boolean isRead;

    @NotNull
    private LocalDateTime createdAt;
}