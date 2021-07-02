package com.example.HelpingHands.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailUtility {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${email}")
    private String email;

    public String sendMail(String email,String mailDesc){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("authenticate otp");
        simpleMailMessage.setText(mailDesc);
        javaMailSender.send(simpleMailMessage);

        return null;
    }
}