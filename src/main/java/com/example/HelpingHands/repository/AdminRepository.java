package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<AdminEntity,Long> {
}
