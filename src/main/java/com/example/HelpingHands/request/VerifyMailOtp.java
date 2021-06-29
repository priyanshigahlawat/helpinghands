package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class VerifyMailOtp {
    private String email;
    private String otp;
}
