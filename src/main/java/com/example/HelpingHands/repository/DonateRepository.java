package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository  extends JpaRepository<DonateEntity,Long> {
    @Query(value = "SELECT max(itemID) FROM donate", nativeQuery = true)
    public Long max();
}
