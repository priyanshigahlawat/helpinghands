package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.DonateEntity;

import com.example.HelpingHands.entity.UserEntity;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepository  extends JpaRepository<DonateEntity,Long> {
    @Query(value = "SELECT max(itemID) FROM donate", nativeQuery = true)
    public Long max();

    @Query(value = "SELECT * FROM donate where categoryID= :id and aprroved_status = 1 and expire_status = 1", nativeQuery = true)
    public List<DonateEntity> getCategoryId(Long id);

    @Query(value = "SELECT * FROM donate ", nativeQuery = true)
    public List<DonateEntity> fetchAllItems();


    @Query(value = "SELECT * FROM donate where userID IN (SELECT userID FROM user WHERE admin_status = 0)", nativeQuery = true)
    public List<DonateEntity> getItems();

}
