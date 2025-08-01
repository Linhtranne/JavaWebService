package ra.edu.model.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    private Integer enrollmentId;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;

    @NotNull
    private LocalDateTime enrollmentDate;

    @NotBlank
    @Pattern(regexp = "ENROLLED|COMPLETED|DROPPED")
    private String status;

    private LocalDateTime completionDate;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal progressPercentage;
}