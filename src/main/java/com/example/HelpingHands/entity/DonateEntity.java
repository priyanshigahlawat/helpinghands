package com.example.HelpingHands.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.*;
import java.util.List;
import java.util.Date;

@Data
@Entity
@Table(name = "donate")
public class DonateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemID;

    @Column
    private String itemName;

    @Column
    private String itemDesc;

    @Column
    private Long ExpireStatus;

    @Column
    private Date date;

    @Column
    private String full_width;

    @Column
    private String thumbnail;

    @Column
    private String portrait;

    @Column
    private String square;

    @Column
    private String hero;

    @Column
    private Long adminID;

    @Column
    private Long userID;

    @Column
    private String adminMessage;

    @Column
    private Long aprrovedStatus;

    @OneToMany(targetEntity = RequestEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID", referencedColumnName = "itemID")
    private List<RequestEntity> getItemID;
}
