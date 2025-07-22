package com.data.service;

import com.data.entity.Notification;
import com.data.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final AccountService accountService;

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByAccountId(UUID accountId) {
        return (List<Notification>) accountService.findByAccountId(accountId);
    }
}
