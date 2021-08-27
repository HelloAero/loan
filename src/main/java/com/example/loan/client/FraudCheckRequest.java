package com.example.loan.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FraudCheckRequest {

    @JsonProperty("client.id")
    private Long clientId;
    private Long loanAmount;
}
