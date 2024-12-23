package com.split.splitthebill.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {
    // Getters and setters
    private String message;
    @JsonProperty("status_code")
    private int statusCode;

    public SuccessResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
