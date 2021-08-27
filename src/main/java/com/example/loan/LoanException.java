package com.example.loan;

import lombok.Getter;

@Getter
public class LoanException extends RuntimeException {

    public LoanException(String reason) {
        super(reason);
    }
}
