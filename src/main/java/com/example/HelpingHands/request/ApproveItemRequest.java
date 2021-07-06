package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class ApproveItemRequest {
    private Long itemID;
    private Long userID;
    private String token;
}