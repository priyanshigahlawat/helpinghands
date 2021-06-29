package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LoginEmailRequest;
import com.example.HelpingHands.request.OtpRequest;
import com.example.HelpingHands.request.VerifyMailOtp;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.MailUtility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.Optional;

@Service
public class LoginViaEmailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    MailUtility mailUtility;

    public PortalResponse sendOtp(@RequestBody LoginEmailRequest req){
        UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());
        OtpEntity otpEntity = new OtpEntity();
        PortalResponse portalResponse = new PortalResponse();

        if (userEntity1 != null) {
            String otp = "";
            String val = "1234567890";
            for(int i = 0; i < 4; i++){
                int z = (int)(Math.random() * val.length());
                otp = otp + z;
            }

            String mailDesc = "Your one time password is " + otp;

            mailUtility.sendMail(req.getEmail(),mailDesc);

            otpEntity.setEmail(req.getEmail());
            otpEntity.setPhone(userEntity1.getPhone());
            otpEntity.setEmailDesc(mailDesc);
            otpEntity.setDate(new Date(System.currentTimeMillis()));
            otpEntity.setOtp(otp);
            otpRepository.save(otpEntity);


            portalResponse.setMessage("Mail send");
            portalResponse.setStatusCode("200");
        } else {
            portalResponse.setMessage("You need to register First");
            portalResponse.setStatusCode("202");
        }

        return portalResponse;
    }

    //=================================================VERIFY OTP=======================================================

    public PortalResponse verifyOtp(@RequestBody VerifyMailOtp req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());
        OtpEntity otpEntity1 = otpRepository.findByEmail(req.getEmail());

        String otp1 = otpEntity1.getOtp();
        if(otp1.equals(req.getOtp())){

            String str = "";
            String val = "1234567890";
            for(int i = 0; i < 4; i++){
                int z = (int)(Math.random() * val.length());
                str = str + z;
            }

            String token= Jwts.builder()
                    .setId(req.getEmail())
                    .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                    .setExpiration(new java.util.Date(System.currentTimeMillis()+1000*100))
                    .signWith(SignatureAlgorithm.HS256,str)
                    .compact();

            userEntity1.setToken(token);
            userEntity1.setSigningKey(str);
            userRepository.save(userEntity1);

            portalResponse.setMessage("Login successful");
            portalResponse.setStatusCode("200");
            portalResponse.setToken(token);
        } else {
            portalResponse.setMessage("Invalid OTP");
            portalResponse.setStatusCode("202");
        }
        return portalResponse;
    }
}
