package ra.edu.mapper;

import ra.edu.model.entity.Review;
import ra.edu.model.dto.request.ReviewDTO;
import ra.edu.model.entity.Course;
import ra.edu.model.entity.User;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .courseId(review.getCourse().getCourseId())
                .studentId(review.getStudent().getUserId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static Review toEntity(ReviewDTO dto, Course course, User student) {
        return Review.builder()
                .reviewId(dto.getReviewId())
                .course(course)
                .student(student)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}