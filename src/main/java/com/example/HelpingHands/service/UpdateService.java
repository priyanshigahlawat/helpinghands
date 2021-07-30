package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.PasswordRequest;
import com.example.HelpingHands.request.UpdateRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UpdateService {
    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    //============================UPDATE OTHER INFORMATION============================================

    public PortalResponse updateInformation(@RequestBody UpdateRequest req) {
        boolean flag = verifyToken.verifyToken(req.getUserID(), req.getToken());
        if (flag == true) {
           Optional<UserEntity> userEntity =userRepository.findById(req.getUserID());
            userEntity.get().setEmail(req.getEmail());
            userEntity.get().setName(req.getName());
            userEntity.get().setPhone(req.getPhone());
            userRepository.save(userEntity.get());
            return PortalResponse.customCountSuccessResponse("The information is updated","");

        } else {
            return PortalResponse.commonErrorResponse("InvalidUser", "", "");

        }
    }

    //============================UPDATE PASSWORD============================================

    public PortalResponse updatePassword(@RequestBody PasswordRequest req) {
        boolean flag = verifyToken.verifyToken(req.getUserID(), req.getToken());
        if (flag == true) {
            Optional<UserEntity> userEntity =userRepository.findById(req.getUserID());
             if(userEntity.get().getPassword().equals(req.getOldPassword())){
              userEntity.get().setPassword(req.getNewPassword());
                 userRepository.save(userEntity.get());
                 return PortalResponse.commonSuccessResponse("Password updated", "",userEntity);
             }
             else{
                 return PortalResponse.commonErrorResponse("Incorect old password", "", "");
             }
        } else {
            return PortalResponse.commonErrorResponse("InvalidUser", "", "");

        }
    }
}
