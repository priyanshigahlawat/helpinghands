package com.example.HelpingHands.request;


import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateRequest {
    private Long userID;
    private String token;
    private String email;
    private String name;
    private Long phone;

}
