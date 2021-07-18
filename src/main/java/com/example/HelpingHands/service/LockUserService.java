package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.CreateAdminRequest;
import com.example.HelpingHands.request.LockUserRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.MailUtility;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class LockUserService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailUtility mailUtility;

    public PortalResponse lockUser(@RequestBody LockUserRequest request){
        boolean flag = verifyToken.verifyToken(request.getAdminID(), request.getToken());
        if(flag == true){
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            userEntity.get().setActiveStatus(0L);
            userRepository.save(userEntity.get());
            String email = userEntity.get().getEmail();
            String mailDesc = "You have been locked by the admin";
            mailUtility.sendMail(email, mailDesc);

            return PortalResponse.commonSuccessResponse("Locked User","",userEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }

    public PortalResponse unlockUser(@RequestBody LockUserRequest request){
        boolean flag = verifyToken.verifyToken(request.getAdminID(), request.getToken());
        if(flag == true){
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            userEntity.get().setActiveStatus(1L);
            userRepository.save(userEntity.get());
            String email = userEntity.get().getEmail();
            String mailDesc = "You are unlocked by the admin";
            mailUtility.sendMail(email, mailDesc);

            return PortalResponse.commonSuccessResponse("Unlocked User","",userEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }
}
