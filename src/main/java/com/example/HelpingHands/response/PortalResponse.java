package com.example.HelpingHands.response;

import lombok.Data;

@Data
public class PortalResponse {
    private String statusCode;
    private String message;
    private String token;
}
