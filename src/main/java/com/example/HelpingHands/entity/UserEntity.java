package com.example.HelpingHands.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @Column
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    @Column
    private String name;

    @Column
    private Long phone;

    @Column
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
    private String password;

    @Column
    private String token;

    @Column
    private Long adminStatus;

    @Column
    private Long activeStatus;

    @Column
    private Long adminID;

    @OneToMany(targetEntity = DonateEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "adminID", referencedColumnName = "userID")
    private List<DonateEntity>getAdminID;

    @OneToMany(targetEntity = OtpEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private List<OtpEntity>getOtpID;
}
