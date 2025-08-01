package ra.edu.mapper;

import ra.edu.model.entity.Course;
import ra.edu.model.dto.request.CourseDTO;
import ra.edu.model.entity.User;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .teacherId(course.getTeacher().getUserId())
                .price(course.getPrice())
                .durationHours(course.getDurationHours())
                .status(course.getStatus().name())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    public static Course toEntity(CourseDTO dto, User teacher) {
        return Course.builder()
                .courseId(dto.getCourseId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .teacher(teacher)
                .price(dto.getPrice())
                .durationHours(dto.getDurationHours())
                .status(Course.Status.valueOf(dto.getStatus()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}