package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class CreateAdminRequest {
    private Long userID;
    private Long adminID;
    private String token;
}