package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveMailOtp {

    @Autowired
    OtpRepository otpRepository;

    public void saveOtpInfo(OtpEntity otpEntity, String mail, Long phone, String mailDesc, String otp){
        otpEntity.setEmail(mail);
        otpEntity.setPhone(phone);
        otpEntity.setEmailDesc(mailDesc);
        otpEntity.setDate(new Date(System.currentTimeMillis()));
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);
    }
}
