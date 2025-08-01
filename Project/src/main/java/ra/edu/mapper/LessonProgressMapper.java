package ra.edu.mapper;


import ra.edu.model.entity.LessonProgress;
import ra.edu.model.dto.request.LessonProgressDTO;
import ra.edu.model.entity.Enrollment;
import ra.edu.model.entity.Lesson;

public class LessonProgressMapper {
    public static LessonProgressDTO toDTO(LessonProgress progress) {
        return LessonProgressDTO.builder()
                .progressId(progress.getProgressId())
                .enrollmentId(progress.getEnrollment().getEnrollmentId())
                .lessonId(progress.getLesson().getLessonId())
                .isCompleted(progress.getIsCompleted())
                .completedAt(progress.getCompletedAt())
                .lastAccessedAt(progress.getLastAccessedAt())
                .build();
    }

    public static LessonProgress toEntity(LessonProgressDTO dto, Enrollment enrollment, Lesson lesson) {
        return LessonProgress.builder()
                .progressId(dto.getProgressId())
                .enrollment(enrollment)
                .lesson(lesson)
                .isCompleted(dto.getIsCompleted())
                .completedAt(dto.getCompletedAt())
                .lastAccessedAt(dto.getLastAccessedAt())
                .build();
    }
}