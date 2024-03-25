package com.pjborowiecki.springbank.loan;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @GetMapping
    public String getLoans() {
        return "Hello from Loans";
    }

}
