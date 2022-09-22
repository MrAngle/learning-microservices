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

    public Notification send(NotificationRequest notificationRequest) {
        Notification notification = Notification.toNotification(
                notificationRequest,
                SENDER_NAME,
                LocalDateTime.now() // sent by service not by user
        );
        return notificationRepository.save(notification);
    }
}