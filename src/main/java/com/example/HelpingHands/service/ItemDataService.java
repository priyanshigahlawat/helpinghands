package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.request.ItemDataRequest;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ItemDataService {

    @Autowired
    DonateRepository donateRepository;

    public PortalResponse itemData (@RequestBody ItemDataRequest request){
        DonateEntity donateEntity = donateRepository.findByItemID(request.getItemID());
        return PortalResponse.commonSuccessResponse("fetched Item Data","",donateEntity);
    }
}