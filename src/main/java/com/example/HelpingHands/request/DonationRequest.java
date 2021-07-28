package com.example.HelpingHands.request;

import lombok.Data;

@Data
public class DonationRequest{
    private  Long userID;
    private String token;
    private Long donorID;
}