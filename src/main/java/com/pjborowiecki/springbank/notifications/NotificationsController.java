package com.pjborowiecki.springbank.notifications;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationsController {

    @GetMapping
    public String getNotifications() {
        return "Hello from Notifications";
    }

}
