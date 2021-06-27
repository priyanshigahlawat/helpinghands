package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository  extends JpaRepository<DonateEntity,Long> {
}
