package ra.edu.mapper;

import ra.edu.model.entity.Course;
import ra.edu.model.entity.Enrollment;
import ra.edu.model.dto.request.EnrollmentDTO;
import ra.edu.model.entity.User;

public class EnrollmentMapper {
    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        return EnrollmentDTO.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .studentId(enrollment.getStudent().getUserId())
                .courseId(enrollment.getCourse().getCourseId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .status(enrollment.getStatus().name())
                .completionDate(enrollment.getCompletionDate())
                .progressPercentage(enrollment.getProgressPercentage())
                .build();
    }

    public static Enrollment toEntity(EnrollmentDTO DTO, User student, Course course) {
        return Enrollment.builder()
                .enrollmentId(DTO.getEnrollmentId())
                .student(student)
                .course(course)
                .enrollmentDate(DTO.getEnrollmentDate())
                .status(Enrollment.Status.valueOf(DTO.getStatus()))
                .completionDate(DTO.getCompletionDate())
                .progressPercentage(DTO.getProgressPercentage())
                .build();
    }
}