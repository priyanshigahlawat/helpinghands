package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class RejectItemRequest {
    private Long itemID;
    private Long userID;
    private String message;
    private String token;
}
