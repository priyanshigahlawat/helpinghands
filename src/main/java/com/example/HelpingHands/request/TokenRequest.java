package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class TokenRequest {
    private String email;
    private String token;
}