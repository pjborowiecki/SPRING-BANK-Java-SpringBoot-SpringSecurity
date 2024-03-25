package com.pjborowiecki.springbank.notification;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @GetMapping
    public String getNotifications() {
        return "Hello from Notifications";
    }

}
