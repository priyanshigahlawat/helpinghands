package com.example.HelpingHands.entity;

import lombok.Data;

import java.util.*;

@Data
public class InboxRecord {
    private String name;
    private String email;
    private Long phone;
    private Long request_id;
    private Long donor_id;
    private Long item_id;
    private String item_name;
    private String item_description;
    private Date date;
    private Long approvedStatus;
    private Long expireStatus;
}
