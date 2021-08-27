package com.example.loan.client;

import com.example.loan.FraudCheckStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FraudCheckResponse {

    private FraudCheckStatus fraudCheckStatus;
    @JsonProperty("rejection.reason")
    private String rejectionReason;

    public boolean isFraud() {
        return fraudCheckStatus == FraudCheckStatus.FRAUD;
    }
}
