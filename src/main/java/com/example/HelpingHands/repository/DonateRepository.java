package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;

import com.example.HelpingHands.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DonateRepository  extends JpaRepository<DonateEntity,Long> {
    public DonateEntity findByItemID(Long itemID);

    @Query(value = "SELECT count( * ) FROM donate", nativeQuery = true)
    public Long max();

    @Query(value = "SELECT max(itemID) FROM donate", nativeQuery = true)
    public Long maxItemID();

    @Query(value = "SELECT * FROM donate where categoryID= :id and aprroved_status=1 and expire_status=0", nativeQuery = true)
    public List<DonateEntity> getCategoryId(Long id);

    @Query(value = "SELECT count( * ) FROM donate where aprroved_status=0", nativeQuery = true)
    public Long getPendingRequests();

    @Query(value = "SELECT * FROM donate ", nativeQuery = true)
    public List<DonateEntity> fetchAllItems();

    @Query(value = "SELECT * FROM donate WHERE aprroved_status=0 and expire_status=0", nativeQuery = true)
    public List<DonateEntity> getItems();

    @Query(value = "SELECT * FROM donate where date >= :date", nativeQuery = true)
    public List<DonateEntity> fetchDateWiseItems(String date);

    @Query(value = "SELECT count( * ) FROM donate where date = :date", nativeQuery = true)
    public Long fetchDonorsADay(String date);

    @Query(value = "SELECT * FROM donate where userid = :userid", nativeQuery = true)
    public List<DonateEntity> fetchUserItems(Long userid);
}
