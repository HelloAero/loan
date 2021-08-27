package com.example.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanResource {
    private final LoanAppService loanAppService;

    @PostMapping
    public void applyLoan(@RequestBody LoanCommand command) {
        loanAppService.loan(command);
    }
}
