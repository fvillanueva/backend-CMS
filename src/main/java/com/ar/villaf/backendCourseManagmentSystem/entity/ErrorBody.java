package com.ar.villaf.backendCourseManagmentSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBody {
    private LocalDateTime timestamp;
    private String error;
    private int status;
    private List<String> message;

    public ErrorBody(String error, int status, List<String> message) {
        this.timestamp= LocalDateTime.now();
        this.error = error;
        this.status = status;
        this.message = message;
    }
}
