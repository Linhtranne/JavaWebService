package ra.edu.model.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Integer reviewId;

    @NotNull
    private Integer courseId;

    @NotNull
    private Integer studentId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}