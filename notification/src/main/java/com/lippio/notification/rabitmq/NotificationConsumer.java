package com.lippio.notification.rabitmq;

import com.lippio.clients.notification.NotificationRequest;
import com.lippio.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * The type Notification consumer.
 */
@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;


    /**
     * Consumer.
     * Catch RabbitMq messages that are in queue
     *
     * @param notificationRequest the notification request
     */
    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
