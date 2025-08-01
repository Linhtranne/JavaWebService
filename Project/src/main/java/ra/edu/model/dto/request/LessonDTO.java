package ra.edu.model.dto.request;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDTO {
    private Integer lessonId;

    @NotNull
    private Integer courseId;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 500)
    private String contentUrl;

    private String textContent;

    @NotNull
    private Integer orderIndex;

    @NotNull
    private Boolean isPublished;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}