package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class RemoveItemRequest {
    private Long userID;
    private String token;
    private Long id;
    private Long itemID;
}