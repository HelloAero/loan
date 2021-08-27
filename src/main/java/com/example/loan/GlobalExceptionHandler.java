package com.example.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LoanException.class})
    protected ExceptionResponse handleConflict(LoanException ex, ServerHttpRequest request) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .path(request.getURI().getPath())
                .build();
    }
}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ExceptionResponse {
    private int status;
    private HttpStatus error;
    private String message;
    private String path;
}
