package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class ItemRequest {
    private Long userID;
    private Long itemID;
    private String token;
}