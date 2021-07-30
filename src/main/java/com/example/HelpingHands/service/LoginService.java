package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LoginEmailRequest;
import com.example.HelpingHands.request.LoginRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.CreateToken;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreateToken createToken;

    public PortalResponse login(@RequestBody LoginRequest req){

        try{
            PortalResponse portalResponse = new PortalResponse();
            UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());

            if(userEntity1 != null && userEntity1.getActiveStatus() == 1){
                if(userEntity1.getPassword().equals(req.getPassword())){

                    String token = createToken.generateToken(req.getEmail());
                    userEntity1.setToken(token);
                    userRepository.save(userEntity1);

                  return  portalResponse.commonSuccessResponse("Login Successfull","",userEntity1);

                } else{
                 return    portalResponse.commonErrorResponse("Password Incorrect","","");
                }

            } else if(userEntity1 == null){
             return   portalResponse.commonErrorResponse("Please sign up first!","","");
            }
            else if(userEntity1.getActiveStatus() == 0){
                return   portalResponse.commonErrorResponse("You have been locked by the admin","","");
            }


        } catch (Exception e){}
        return null;
    }
}
