package com.pjborowiecki.springbank.balance;

package com.pjborowiecki.springbank.balance;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    @GetMapping
    public String getBalance() {
        return "Hello from Balance";
    }

}
