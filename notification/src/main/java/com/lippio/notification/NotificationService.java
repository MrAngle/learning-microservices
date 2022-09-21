package com.lippio.notification;

import com.lippio.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private static final String SENDER_NAME = "SENDER_NOTIFICATION";

    private NotificationRepository notificationRepository;

    public Notification saveToDb(NotificationRequest notificationRequest) {
        Notification notification = Notification.toNotification(
                notificationRequest, SENDER_NAME, LocalDateTime.now());
        return notificationRepository.save(notification);
    }
}