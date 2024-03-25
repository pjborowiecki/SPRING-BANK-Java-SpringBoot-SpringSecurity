package com.pjborowiecki.springbank.contact;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    @GetMapping
    public String saveContactDetails() {
        return "Hello from contact";
    }
}
