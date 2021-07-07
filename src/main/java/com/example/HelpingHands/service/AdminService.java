package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.ApproveItem;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.request.ApproveItemRequest;
import com.example.HelpingHands.request.RejectItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.ImageResponse;
import com.example.HelpingHands.utility.VerifyToken;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    ImageResponse imageResponse;


    public PortalResponse fetchData(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
            if(flag == true){

                List<DonateEntity> donateEntities =  donateRepository.getItems();

                List<DonateEntity> donateEntityList = imageResponse.imageResponse(donateEntities);

                System.out.println(donateEntities);
                return PortalResponse.customSuccessResponse("Fetched data","",donateEntityList);
            }
            else {
                return PortalResponse.commonErrorResponse("InvalidUser","","");
            }
        } catch(Exception ex){}
        return null;
    }

    //===========================================APPROVE ITEMS=============================================

    public PortalResponse approveItem(@RequestBody ApproveItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = approveItem.approveItem(request);
            return PortalResponse.commonSuccessResponse("Approved", "",donateEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }

    //===========================================APPROVE ITEMS=============================================

    public PortalResponse rejectItem(@RequestBody RejectItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = approveItem.rejectItem(request);
            return PortalResponse.commonSuccessResponse(request.getMessage(), "",donateEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }

}