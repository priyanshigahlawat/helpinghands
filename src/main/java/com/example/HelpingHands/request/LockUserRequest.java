package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class LockUserRequest {
    private Long userID;
    private Long adminID;
    private String token;
    private Long activeStatus;
}
