package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class VerifySmsOtp {
    private Long phone;
    private String otp;
}
