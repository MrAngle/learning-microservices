package com.lippio.notification;

import com.lippio.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> saveNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);
        return new ResponseEntity<>(notificationService.saveToDb(notificationRequest).toString(), HttpStatus.OK);
    }
}
