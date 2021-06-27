package com.example.HelpingHands.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @Column
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column
    private String name;

    @Column
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")
    private Long phone;

    @Column
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")
    private String password;

    @Column
    private String token;

    @Column
    private Long adminStatus;

    @Column
    private Long adminID;

    @OneToMany(targetEntity = DonateEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private List<DonateEntity>getUserID;

    @OneToMany(targetEntity = DonateEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "adminID", referencedColumnName = "userID")
    private List<DonateEntity>getAdminID;

    @OneToMany(targetEntity = RequestEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "reqID", referencedColumnName = "userID")
    private List<RequestEntity>getReqID;

    @OneToMany(targetEntity = RequestEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "donorID", referencedColumnName = "userID")
    private List<RequestEntity>getDonorID;

    @OneToMany(targetEntity = OtpEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private List<OtpEntity>getOtpID;
}
