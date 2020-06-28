package com.epam.bookservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public ApiError(String message, HttpStatus status){
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }
}
