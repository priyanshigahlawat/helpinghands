package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.ApproveItem;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.ApproveItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AdminService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    ApproveItem approveItem;

    @Autowired
    DonateRepository donateRepository;

    public PortalResponse fetchData(@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){

            List<DonateEntity> donateEntities =  donateRepository.getItems();

            System.out.println(donateEntities);
            return PortalResponse.customSuccessResponse("Fetched data","",donateEntities);
        }
        else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }

    //===========================================APPROVE ITEMS=============================================

    public PortalResponse approveItem(@RequestBody ApproveItemRequest request){
        Optional<DonateEntity> donateEntity = approveItem.appriveItem(request);
        return PortalResponse.commonSuccessResponse(request.getMessage(), "",donateEntity);
    }
}