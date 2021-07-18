package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class AcceptItemRequest {
    private Long userID;
    private String token;
    private Long itemID;
}
