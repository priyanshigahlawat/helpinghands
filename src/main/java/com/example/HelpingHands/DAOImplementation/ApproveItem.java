package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.request.ApproveItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApproveItem {

    @Autowired
    DonateRepository donateRepository;

    public Optional<DonateEntity> appriveItem(ApproveItemRequest request){
        Optional<DonateEntity> donateEntity = donateRepository.findById(request.getItemID());
        donateEntity.get().setAprrovedStatus(request.getApproveStatus());
        donateEntity.get().setAdminMessage(request.getMessage());
        donateRepository.save(donateEntity.get());
        return donateEntity;
    }
}
