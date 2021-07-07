package com.example.HelpingHands.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date dateOfReq;

    @Column
    private Date dateOfApproval;

}
