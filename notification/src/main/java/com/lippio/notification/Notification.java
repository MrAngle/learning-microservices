package com.lippio.notification;

import com.lippio.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

/**
 * The type Notification.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Long notificationId;
    private Long toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;


    /**
     * To notification.
     *  (If business logic is to keep all field as optional, You can use this method.
     *  Otherwise, use method with required fields)
     *
     * @param notificationRequest the notification request
     * @return the notification
     */
    public static Notification toNotification(NotificationRequest notificationRequest) {
        return Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .build();
    }

    /**
     * To notification.
     *  (If business logic is to keep all field as optional, You can use this method.
     *  Otherwise, use method with required fields)
     *
     * @param notificationRequest the notification request
     * @return the notification
     */
    public static Notification toNotification(NotificationRequest notificationRequest, String sender, LocalDateTime sentAt) {
        return Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender(sender)
                .sentAt(sentAt)
                .build();
    }
}