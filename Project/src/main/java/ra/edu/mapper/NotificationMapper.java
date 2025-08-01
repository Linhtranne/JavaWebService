package ra.edu.mapper;

import ra.edu.model.entity.Notification;
import ra.edu.model.dto.request.NotificationDTO;
import ra.edu.model.entity.User;

public class NotificationMapper {
    public static NotificationDTO toDTO(Notification notification) {
        return NotificationDTO.builder()
                .notificationId(notification.getNotificationId())
                .userId(notification.getUser().getUserId())
                .message(notification.getMessage())
                .type(notification.getType())
                .targetUrl(notification.getTargetUrl())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public static Notification toEntity(NotificationDTO dto, User user) {
        return Notification.builder()
                .notificationId(dto.getNotificationId())
                .user(user)
                .message(dto.getMessage())
                .type(dto.getType())
                .targetUrl(dto.getTargetUrl())
                .isRead(dto.getIsRead())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}