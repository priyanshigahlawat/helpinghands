package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.CategoryEntity;
import com.example.HelpingHands.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity,Long> {
}
