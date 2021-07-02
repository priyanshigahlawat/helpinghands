package com.example.HelpingHands.utility;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PhoneUtility {

    @Value("${smsUsername}")
    private String userName;

    @Value("${smsPassword}")
    private String userPassword;

    @Value("${smsPhone}")
    private String userPhone;

    public String sendSms(Long phone,String smsDesc){

        String phone2 = "+91" + phone;
        Twilio.init(userName,userPassword);
        Message.creator(new PhoneNumber(phone2),new PhoneNumber(userPhone), smsDesc).create();

        return null;
    }

}
