package com.pjborowiecki.springbank.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {

    @GetMapping
    public String welcome() {
        return "Welcome to Spring Boot with Spring Security!";
    }

}
