package com.project.insurance.app.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private Map<String, String> fieldErrors;

    public ErrorResponse(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String message,
            String path,
            Map<String, String> fieldErrors) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }
    public ErrorResponse(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String message,
            String path) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }



}
