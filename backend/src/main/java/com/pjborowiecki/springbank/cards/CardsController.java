package com.pjborowiecki.springbank.cards;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {

    @GetMapping
    public String getCards() {
        return "Hello from cards!";
    }
}
