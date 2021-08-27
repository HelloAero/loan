package com.example.loan.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FraudService", url = "${services.fraud.url}")
public interface FraudClient {

    @PutMapping("/fraudcheck")
    FraudCheckResponse check(@RequestBody FraudCheckRequest request);
}
