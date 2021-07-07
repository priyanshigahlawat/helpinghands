package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.CreateAdminRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CreateAdminService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    public PortalResponse createAdmin(@RequestBody CreateAdminRequest request){
        boolean flag = verifyToken.verifyToken(request.getAdminID(), request.getToken());
        if(flag == true){
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            userEntity.get().setAdminStatus(1L);
            userEntity.get().setAdminID(request.getAdminID());
            userRepository.save(userEntity.get());

            return PortalResponse.commonSuccessResponse("Made admin","",userEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("Invalid User","","");
        }
    }
}
