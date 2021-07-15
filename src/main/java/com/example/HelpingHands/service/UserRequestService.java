package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserRequestService {
    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    public PortalResponse myItems(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
            if(flag == true){
//                List<UserEntity> userEntity = userRepository.fetchUserInbox(request.getUserID());
//                return PortalResponse.commonSuccessResponse("Fetched Data","", userEntity);
            }
            else {
                return PortalResponse.commonErrorResponse("invalid user", "", "");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return  PortalResponse.commonErrorResponse("No matched record found","","");
        }
        return null;
    }
}