package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DonateRepository  extends JpaRepository<DonateEntity,Long> {
    @Query(value = "SELECT max(itemID) FROM donate", nativeQuery = true)
    public Long max();

    @Query(value = "SELECT * FROM donate where categoryID= :id", nativeQuery = true)
    public List<DonateEntity> getCategoryId(Long id);

    @Query(value = "SELECT * FROM donate ", nativeQuery = true)
    public List<DonateEntity> fetchAllItems();


    @Query(value = "SELECT * FROM donate where userID IN (SELECT userID FROM user WHERE admin_status = 0)", nativeQuery = true)
    public List<DonateEntity> getItems();

    @Query(value = "SELECT * FROM donate where date >= :date", nativeQuery = true)
    public List<DonateEntity> fetchDateWiseItems(String date);

}
