package com.example.loan;

import com.example.loan.client.FraudCheckRequest;
import com.example.loan.client.FraudClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanAppService {

    private final FraudClient fraudClient;

    public void loan(LoanCommand command) {
        var request = new FraudCheckRequest(command.getClientId(), command.getLoanAmount());
        var fraudCheckResponse = fraudClient.check(request);
        if (fraudCheckResponse.isFraud()) {
            throw new LoanException(fraudCheckResponse.getRejectionReason());
        }
    }
}
