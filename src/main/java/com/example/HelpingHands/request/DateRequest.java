package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class DateRequest {
    private String fetchDate;
    private Long userID;
    private String token;
}
