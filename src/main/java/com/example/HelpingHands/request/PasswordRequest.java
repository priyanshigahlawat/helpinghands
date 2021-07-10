package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String newPassword;
    private String oldPassword;
    private Long userID;
    private String token;
}
