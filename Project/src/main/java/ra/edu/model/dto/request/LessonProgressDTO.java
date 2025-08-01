package ra.edu.model.dto.request;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonProgressDTO {
    private Integer progressId;

    @NotNull
    private Integer enrollmentId;

    @NotNull
    private Integer lessonId;

    @NotNull
    private Boolean isCompleted;

    private LocalDateTime completedAt;

    @NotNull
    private LocalDateTime lastAccessedAt;
}