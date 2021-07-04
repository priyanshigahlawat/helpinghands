package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.OtpEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveSmsOtp {

    @Autowired
    OtpRepository otpRepository;

    public void saveOtpInfo(OtpEntity otpEntity, UserEntity userEntity,String otp){
        otpEntity.setEmail(userEntity.getEmail());
        otpEntity.setPhone(userEntity.getPhone());
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);
    }
}
