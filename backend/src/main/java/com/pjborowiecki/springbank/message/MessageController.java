package com.pjborowiecki.springbank.message;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    @GetMapping
    public String saveContactDetails() {
        return "Hello from contact";
    }
}
