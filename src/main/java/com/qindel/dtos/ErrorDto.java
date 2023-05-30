package com.qindel.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class ErrorDto {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    public ErrorDto(HttpStatus httpStatus, String message) {
        this.timestamp = Instant.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }
}
