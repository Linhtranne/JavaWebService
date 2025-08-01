package ra.edu.model.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Integer courseId;

    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    @NotNull
    private Integer teacherId;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

    private Integer durationHours;

    @NotBlank
    @Pattern(regexp = "DRAFT|PUBLISHED|ARCHIVED")
    private String status;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}
