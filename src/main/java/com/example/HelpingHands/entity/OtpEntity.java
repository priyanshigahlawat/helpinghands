package com.example.HelpingHands.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.Date;

@Data
@Entity
@Table(name = "otp")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String otp;

    @Column
    private Long phone;

    @Column
    private String email;

    @Column
    private Date date;

    @Column
    private String emailDesc;

    @Column
    private Long status;
}
