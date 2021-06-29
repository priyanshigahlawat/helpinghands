package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.RegisterRequest;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    public PortalResponse saveInfo(@RequestBody @Valid RegisterRequest req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());

        try{
            if(userEntity1 == null){
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(req.getEmail());
                userEntity.setName(req.getName());
                userEntity.setPhone(req.getPhone());
                userEntity.setPassword(req.getPassword());
                userEntity.setAdminStatus(0L);
                userRepository.save(userEntity);
                portalResponse.setMessage("Record saved");
                portalResponse.setStatusCode("200");
            }else {
                portalResponse.setMessage("record already present");
                portalResponse.setStatusCode("202");
            }

        } catch (ConstraintViolationException ex){
            portalResponse.setMessage("invalid email or password");
            portalResponse.setStatusCode("202");
        }
        return portalResponse;
    }

}
