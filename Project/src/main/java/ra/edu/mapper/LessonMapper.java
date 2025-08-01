package ra.edu.mapper;
import ra.edu.model.entity.Lesson;
import ra.edu.model.dto.request.LessonDTO;
import ra.edu.model.entity.Course;

public class LessonMapper {
    public static LessonDTO toDTO(Lesson lesson) {
        return LessonDTO.builder()
                .lessonId(lesson.getLessonId())
                .courseId(lesson.getCourse().getCourseId())
                .title(lesson.getTitle())
                .contentUrl(lesson.getContentUrl())
                .textContent(lesson.getTextContent())
                .orderIndex(lesson.getOrderIndex())
                .isPublished(lesson.getIsPublished())
                .createdAt(lesson.getCreatedAt())
                .updatedAt(lesson.getUpdatedAt())
                .build();
    }

    public static Lesson toEntity(LessonDTO dto, Course course) {
        return Lesson.builder()
                .lessonId(dto.getLessonId())
                .course(course)
                .title(dto.getTitle())
                .contentUrl(dto.getContentUrl())
                .textContent(dto.getTextContent())
                .orderIndex(dto.getOrderIndex())
                .isPublished(dto.getIsPublished())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}