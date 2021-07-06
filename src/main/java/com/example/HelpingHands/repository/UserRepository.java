package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);

    public UserEntity findByPhone(Long phone);

    @Query(value = "SELECT count( * ) FROM user", nativeQuery = true)
    public Long max();

    @Query(value = "SELECT max(itemID) FROM user where date = :date", nativeQuery = true)
    public Long fetchRegistersADay(String date);
}