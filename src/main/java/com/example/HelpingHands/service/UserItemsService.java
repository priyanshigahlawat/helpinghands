package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.AcceptItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.MailUtility;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserItemsService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    DonateRepository donateRepository;

    @Autowired
    MailUtility mailUtility;

    @Autowired
    UserRepository userRepository;

    public PortalResponse myItems(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
            if(flag == true){
                List<DonateEntity> donateEntity = donateRepository.fetchUserItems(request.getUserID());
                return PortalResponse.commonSuccessResponse("Fetched Data","", donateEntity);
            }
            else {
                return PortalResponse.commonErrorResponse("invalid user", "", "");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return  PortalResponse.commonErrorResponse("No matched record found","","");
        }

    }
    //=======================================REMOVE ITEMS===============================================================

    public PortalResponse removeItems(@RequestBody AcceptItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            DonateEntity donateEntity = donateRepository.findByItemID(request.getItemID());
            donateRepository.delete(donateEntity);
            Long id = donateEntity.getUserID();
            String itemName = donateEntity.getItemName();
            Optional<UserEntity> userEntity = userRepository.findById(id);
            String email = userEntity.get().getEmail();
            String mailDesc = "You just removed " + itemName;
            mailUtility.sendMail(email, mailDesc);
            return PortalResponse.commonSuccessResponse("Removed item","", "");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }
}