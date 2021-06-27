package com.example.HelpingHands.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long pendingRequests;

    @Column
    private Long totalAcc;
}
