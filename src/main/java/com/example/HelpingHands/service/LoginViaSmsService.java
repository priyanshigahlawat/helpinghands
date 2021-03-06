package com.example.HelpingHands.service;

import com.example.HelpingHands.DAOImplementation.SaveSmsOtp;
import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.LoginEmailRequest;
import com.example.HelpingHands.request.LoginSmsRequest;
import com.example.HelpingHands.request.VerifyMailOtp;
import com.example.HelpingHands.request.VerifySmsOtp;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.CreateToken;
import com.example.HelpingHands.utility.MailUtility;
import com.example.HelpingHands.utility.PhoneUtility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class LoginViaSmsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    PhoneUtility phoneUtility;

    @Autowired
    CreateToken createToken;

    @Autowired
    SaveSmsOtp saveSmsOtp;

    @Value("${signingKey}")
    private String key;

    public PortalResponse sendOtp(@RequestBody LoginSmsRequest req){

        UserEntity userEntity1 = userRepository.findByPhone(req.getPhone());
        OtpEntity otpEntity = otpRepository.findByPhone(req.getPhone());
        if(otpEntity == null){
            OtpEntity otpEntity1 = new OtpEntity();
            otpEntity = otpEntity1;
        }
        PortalResponse portalResponse = new PortalResponse();
        if (userEntity1 != null) {
            String otp = "";
            String val = "1234567890";
            for(int i = 0; i < 4; i++){
                int z = (int)(Math.random() * val.length());
                otp = otp + z;
            }

            String smsDesc = "Your one time password is " + otp;
            phoneUtility.sendSms(req.getPhone(),smsDesc);
            saveSmsOtp.saveOtpInfo(otpEntity,userEntity1,otp);
            return portalResponse.commonSuccessResponse("Sms send","",otpEntity);
        }
        else {
            return   portalResponse.commonErrorResponse("You need to register First","","");
        }

    }

    //=================================================VERIFY OTP=======================================================

    public PortalResponse verifyOtp(@RequestBody VerifySmsOtp req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity1 = userRepository.findByPhone(req.getPhone());
        OtpEntity otpEntity1 = otpRepository.findByPhone(req.getPhone());
        String otp1 = otpEntity1.getOtp();
        if(otp1.equals(req.getOtp())){
            String token = createToken.generateToken(userEntity1.getEmail());
            userEntity1.setToken(token);
            userRepository.save(userEntity1);
            return  portalResponse.commonSuccessResponse("Login successfull","",userEntity1);
        }
        else {
            return    portalResponse.commonErrorResponse("Invalid OTP","","");
        }
    }
}
