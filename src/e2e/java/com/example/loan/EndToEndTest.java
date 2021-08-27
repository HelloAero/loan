package com.example.loan;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.webflux.WebTestClientInitializer;
import com.example.loan.snippet.SectionBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.ExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.function.Consumer;

import static capital.scalable.restdocs.response.ResponseModifyingPreprocessors.limitJsonArrayLength;
import static capital.scalable.restdocs.response.ResponseModifyingPreprocessors.replaceBinaryContent;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@SpringBootTest(classes = LoanApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureStubRunner
public abstract class EndToEndTest {

    @Autowired
    private ObjectMapper objectMapper;
    protected WebTestClient webTestClient;

    @BeforeEach
    protected void setUp(RestDocumentationContextProvider provider, ApplicationContext context) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .responseTimeout(Duration.ofMillis(30000))
                .baseUrl("http://localhost:8080/")
                .filter(WebTestClientRestDocumentation.documentationConfiguration(provider)
                        .snippets()
                        .withDefaults(WebTestClientInitializer.prepareSnippets(context),
                                CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.embedded(),
                                new SectionBuilder().addSnippetNames("custom-auto-section").skipEmpty(true).build()))
                .entityExchangeResultConsumer(commonDocumentation())
                .build();
    }

    private <T extends ExchangeResult> Consumer<T> commonDocumentation(final Snippet... snippets) {
        return document("{class-name}/{method-name}", preprocessRequest(),
                this.commonResponsePreprocessor(), snippets);
    }

    private OperationResponsePreprocessor commonResponsePreprocessor() {
        return preprocessResponse(replaceBinaryContent(),
                limitJsonArrayLength(this.objectMapper), prettyPrint());
    }
}
