package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository  extends JpaRepository<OtpEntity,Long> {
    public OtpEntity findByEmail(String email);
    public OtpEntity findByPhone(Long phone);
}
