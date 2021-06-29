package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RecipientController {

    @Autowired
    RegisterService registerService;

    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;

    @Autowired
    LoginViaEmailService loginEmailService;

    @Autowired
    LoginViaSmsService loginSmsService;

    @PostMapping("/register")
    public PortalResponse saveInfo(@RequestBody @Valid RegisterRequest req){
        return registerService.saveInfo(req);
    }

    @PostMapping("/login")
    @CrossOrigin
    public  PortalResponse login(@RequestBody LoginRequest req){
        return loginService.login(req);
    }

    @PostMapping("/verifyToken")
    public PortalResponse verify(@RequestBody TokenRequest req){
        return tokenService.verifyToken(req);
    }

    @PostMapping("/loginMail")
    public PortalResponse sendMailOtp(@RequestBody LoginEmailRequest req){
        return loginEmailService.sendOtp(req);
    }

    @PostMapping("/verifyMailOtp")
    public PortalResponse verifyMailOtp(@RequestBody VerifyMailOtp req){
        return loginEmailService.verifyOtp(req);
    }

    @PostMapping("/loginSms")
    public PortalResponse sendSmsOtp(@RequestBody LoginSmsRequest req){
        return loginSmsService.sendOtp(req);
    }

    @PostMapping("/veifySmsOtp")
    public PortalResponse verifySmsOtp(@RequestBody VerifySmsOtp req){
        return loginSmsService.verifyOtp(req);
    }

}
