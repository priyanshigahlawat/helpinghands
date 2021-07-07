package com.example.HelpingHands.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;

    @Column
    private String categoryName;

    @OneToMany(targetEntity = DonateEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private List<DonateEntity>getCategoryID;

}