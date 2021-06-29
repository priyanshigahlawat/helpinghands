package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String name;
    private Long phone;
    private String password;
}
