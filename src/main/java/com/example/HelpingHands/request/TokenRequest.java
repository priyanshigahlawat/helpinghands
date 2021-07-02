package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class TokenRequest {
    private Long userID;
    private String token;
}