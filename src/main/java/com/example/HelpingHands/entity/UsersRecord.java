package com.example.HelpingHands.entity;

import lombok.Data;

@Data
public class UsersRecord {
    private UserEntity userEntity;
    private Long totalDonations;
    private Long acceptedReq;
    private Long rejectedReq;
}
