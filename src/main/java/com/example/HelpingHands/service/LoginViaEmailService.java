package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.SaveMailOtp;
import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LoginEmailRequest;
import com.example.HelpingHands.request.OtpRequest;
import com.example.HelpingHands.request.VerifyMailOtp;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.CreateToken;
import com.example.HelpingHands.utility.MailUtility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    CreateToken createToken;

    @Autowired
    SaveMailOtp saveMailOtp;

    @Value("${signingKey}")
    private String key;

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

            String mailDesc = "Your one time password is " + otp + "   And your password is: " + userEntity1.getPassword();
            mailUtility.sendMail(req.getEmail(),mailDesc);
            saveMailOtp.saveOtpInfo(otpEntity,req.getEmail(), userEntity1.getPhone(),mailDesc,otp);

            return portalResponse.commonSuccessResponse("Mail send","",otpEntity);
        } else {
          return   portalResponse.commonErrorResponse("You need to register First","","");
        }

    }

    //=================================================VERIFY OTP=======================================================

    public PortalResponse verifyOtp(@RequestBody VerifyMailOtp req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity1 = userRepository.findByEmail(req.getEmail());
        OtpEntity otpEntity1 = otpRepository.findByEmail(req.getEmail());

        String otp1 = otpEntity1.getOtp();
        if(otp1.equals(req.getOtp())){

            String token = createToken.generateToken(req.getEmail());

            userEntity1.setToken(token);
            userRepository.save(userEntity1);


          return  portalResponse.commonSuccessResponse("Login successfull","",userEntity1);
        } else {
        return   portalResponse.commonErrorResponse("Invalid OTP","","");
        }
    }
}
