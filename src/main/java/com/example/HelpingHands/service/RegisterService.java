package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.RegisterRequest;
import com.example.HelpingHands.request.VerifyMailOtp;
import com.example.HelpingHands.response.PortalResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    @Autowired
    OtpRepository otpRepository;

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

            return  portalResponse.commonSuccessResponse("Record saved","",userEntity);
            }else {
             return  portalResponse.commonErrorResponse("record already present","","");

            }

        } catch (ConstraintViolationException ex){
           return portalResponse.commonErrorResponse("invalid email or password","","");
        }
    }

    //=========================================VERIFY OTP===============================================================

    public PortalResponse verifyOtp(@RequestBody VerifyMailOtp req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());
        OtpEntity otpEntity1 = otpRepository.findByEmail(req.getEmail());

        String otp1 = otpEntity1.getOtp();
        if(otp1.equals(req.getOtp())){
           return portalResponse.commonSuccessResponse("Login successful","","");
        } else {
          return  portalResponse.commonErrorResponse("Invalid OTP","","");
        }
    }
}
