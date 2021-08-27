package com.example.loan;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.hamcrest.Matchers.is;

class LoanResourceTest extends EndToEndTest {

    @Test
    void should_loan_successfully() {
        // given:
        var givenLoan = new LoanCommand(1234567890L, 99999L);
        // when:
        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/loans")
                .body(BodyInserters.fromValue(givenLoan))
                .exchange();
        // then:
        responseSpec.expectStatus().is2xxSuccessful()
                .expectBody().isEmpty();
    }

    @Test
    void should_loan_failure_when_amount_too_high() throws Exception {
        // given:
        var givenLoan = new LoanCommand(9876543210L, 999999999L);
        // when:
        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/loans")
                .body(BodyInserters.fromValue(givenLoan))
                .exchange();
        // then:
        responseSpec
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.message", is("Amount too high"));
    }
}
