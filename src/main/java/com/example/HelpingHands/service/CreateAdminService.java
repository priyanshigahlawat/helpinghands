package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.CreateAdminRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.MailUtility;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.*;

@Service
public class CreateAdminService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailUtility mailUtility;

    public PortalResponse fetchUsers(@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            List<UserEntity> userEntityList = userRepository.fetchUsers();
            return PortalResponse.commonSuccessResponse("Fetched record","",userEntityList);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }

    //==========================================FETCH TOTAL USERS========================================================

    public PortalResponse fetchTotalUsers(@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            List<UserEntity> userEntityList = userRepository.fetchTotalUsers();
            return PortalResponse.commonSuccessResponse("Fetched record","",userEntityList);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }

    //==========================================FETCH TOTAL USERS========================================================

    public PortalResponse fetchTotalLockUsers(@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(), request.getToken());
        if(flag == true){
            List<UserEntity> userEntityList = userRepository.fetchLockUsers();
            return PortalResponse.commonSuccessResponse("Fetched record","",userEntityList);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }

    //=============================CREATE ADMIN=========================================================================

    public PortalResponse createAdmin(@RequestBody CreateAdminRequest request){
        boolean flag = verifyToken.verifyToken(request.getAdminID(), request.getToken());
        if(flag == true){
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            userEntity.get().setAdminStatus(1L);
            userEntity.get().setAdminID(request.getAdminID());
            userRepository.save(userEntity.get());
            String email = userEntity.get().getEmail();
            String mailDesc = "You are now an admin";
            mailUtility.sendMail(email, mailDesc);

            return PortalResponse.commonSuccessResponse("Made admin","",userEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }

    //=============================REMOVE ADMIN=========================================================================

    public PortalResponse removeAdmin(@RequestBody CreateAdminRequest request){
        boolean flag = verifyToken.verifyToken(request.getAdminID(), request.getToken());
        if(flag == true){
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            userEntity.get().setAdminStatus(0L);
            userEntity.get().setAdminID(request.getAdminID());
            userRepository.save(userEntity.get());
            String email = userEntity.get().getEmail();
            String mailDesc = "You are no longer an admin";
            mailUtility.sendMail(email, mailDesc);

            return PortalResponse.commonSuccessResponse("Removed admin","",userEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }
}
