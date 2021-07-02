package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LoginEmailRequest;
import com.example.HelpingHands.request.LoginRequest;
import com.example.HelpingHands.response.PortalResponse;
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

    @Value("${signingKey}")
    private String key;

    public PortalResponse login(@RequestBody LoginRequest req){

        try{
            PortalResponse portalResponse = new PortalResponse();
            UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());

            if(userEntity1 != null){
                if(userEntity1.getPassword().equals(req.getPassword())){


                    String token= Jwts.builder()
                            .setId(req.getEmail())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis()+1000*100))
                            .signWith(SignatureAlgorithm.HS256,key)
                            .compact();

                    userEntity1.setToken(token);
                    userRepository.save(userEntity1);


                  return  portalResponse.commonSuccessResponse("Login Successfull","",userEntity1);

                } else{
                 return    portalResponse.commonErrorResponse("Password Incorrect","","");
                }

            } else {
             return   portalResponse.commonErrorResponse("Record not found","","");
            }


        } catch (Exception e){}
        return null;
    }
}
