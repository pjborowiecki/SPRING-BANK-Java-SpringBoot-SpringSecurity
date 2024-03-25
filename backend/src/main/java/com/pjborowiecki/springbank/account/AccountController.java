package com.pjborowiecki.springbank.account;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @GetMapping
    public String getAccount() {
        return "Hello from Account";
    }

}
