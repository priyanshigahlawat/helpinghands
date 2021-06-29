package com.example.HelpingHands.utility;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class PhoneUtility {

    public String sendSms(Long phone,String smsDesc){

        String phone2 = "+91" + phone;
        Twilio.init("ACd68044b083af93eb0d4218e3dcc8f2eb","3ec9afe28e12025d2401b14cccdd42b0");
        Message.creator(new PhoneNumber(phone2),new PhoneNumber("+13142793269"), smsDesc).create();

        return null;
    }

}
