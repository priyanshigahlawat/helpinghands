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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public PortalResponse login(@RequestBody LoginRequest req){

        try{
            PortalResponse portalResponse = new PortalResponse();
            UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());

            if(userEntity1 != null){
                if(userEntity1.getPassword().equals(req.getPassword())){

                    String str = "";
                    String val = "1234567890";
                    for(int i = 0; i < 4; i++){
                        int z = (int)(Math.random() * val.length());
                        str = str + z;
                    }

                    String token= Jwts.builder()
                            .setId(req.getEmail())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis()+1000*100))
                            .signWith(SignatureAlgorithm.HS256,str)
                            .compact();

                    userEntity1.setToken(token);
                    userEntity1.setSigningKey(str);
                    userRepository.save(userEntity1);

                    portalResponse.setMessage("Login Successfull");
                    portalResponse.setStatusCode("200");
                    portalResponse.setToken(token);

                } else{
                    portalResponse.setMessage("Password Incorrect");
                    portalResponse.setStatusCode("202");
                }

            } else {
                portalResponse.setMessage("Record not found");
                portalResponse.setStatusCode("202");
            }

            return portalResponse;

        } catch (Exception e){}
        return null;
    }
}
