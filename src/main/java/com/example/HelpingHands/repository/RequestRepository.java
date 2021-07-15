package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.RequestEntity;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity,String> {
}
