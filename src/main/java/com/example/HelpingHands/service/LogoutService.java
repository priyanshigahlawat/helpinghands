package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LogoutRequest;
import com.example.HelpingHands.response.PortalResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class LogoutService {

    @Autowired
    UserRepository userRepository;

    public PortalResponse logoutUser(@RequestBody LogoutRequest request){
        Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
        userEntity.get().setToken(null);
        userRepository.save(userEntity.get());
        return PortalResponse.customCountSuccessResponse("Logout Successful","");
    }
}
