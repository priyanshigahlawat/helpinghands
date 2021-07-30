package com.example.HelpingHands.entity;
import lombok.Data;

import java.util.List;

@Data
public class CatEntity {
    private Long categoryID;
    private String categoryName;
    private List<DonateEntity> getCategoryID;
}
