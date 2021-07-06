package com.example.HelpingHands.request;

import lombok.Data;

import java.sql.Date;

@Data
public class DonorsRequest {
    Date date;
    Long userID;
    String token;
}
