package com.example.HelpingHands.request;


import lombok.Data;

@Data
public class CategoryRequest {
    private Long categoryID;
    private  String categoryName;
    private Long userID;
    private String token;
}
