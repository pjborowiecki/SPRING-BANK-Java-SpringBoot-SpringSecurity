package com.pjborowiecki.springbank.card;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @GetMapping
    public String getCards() {
        return "Hello from cards!";
    }
}
