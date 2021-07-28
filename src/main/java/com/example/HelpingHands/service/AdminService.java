package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.ApproveItem;
import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.ApproveItemRequest;
import com.example.HelpingHands.request.RejectItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.ImageResponse;
import com.example.HelpingHands.utility.MailUtility;
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
    UserRepository userRepository;

    @Autowired
    MailUtility mailUtility;

    public PortalResponse fetchData(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
            if(flag == true){

                List<DonateEntity> donateEntities =  donateRepository.fetchAllItems();

//                List<DonateEntity> donateEntityList = imageResponse.imageResponse(donateEntities);

                System.out.println(donateEntities);
                return PortalResponse.customSuccessResponse("Fetched data","",donateEntities);
            }
            else {
                return PortalResponse.commonErrorResponse("InvalidUser","","");
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return  PortalResponse.commonErrorResponse("No matched record found","","");
        }
    }

    //===========================================APPROVE ITEMS=============================================

    public PortalResponse approveItem(@RequestBody ApproveItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = approveItem.approveItem(request);
            Long id = donateEntity.get().getUserID();
            Optional<UserEntity> userEntity = userRepository.findById(id);
            String email = userEntity.get().getEmail();
            String mailDesc = "Your item has been approved by Admin. Keep donating for the needy";
            mailUtility.sendMail(email, mailDesc);
            return PortalResponse.commonSuccessResponse("Approved", "",donateEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }

    //===========================================REJECT ITEMS=============================================

    public PortalResponse rejectItem(@RequestBody RejectItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = approveItem.rejectItem(request);
            Long id = donateEntity.get().getUserID();
            Optional<UserEntity> userEntity = userRepository.findById(id);
            String email = userEntity.get().getEmail();
            String mailDesc = "Sorry! Your item has been rejected by Admin. Try donating something else";
            mailUtility.sendMail(email, mailDesc);
            return PortalResponse.commonSuccessResponse(request.getMessage(), "",donateEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("InvalidUser","","");
        }
    }

}