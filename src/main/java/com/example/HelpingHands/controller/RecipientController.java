package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.Port;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
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

    @Autowired
    ImageService imageService;

    @PostMapping("/register")
    public PortalResponse saveInfo(@RequestBody @Valid RegisterRequest req){
        return registerService.saveInfo(req);
    }

    @PostMapping("/verifyRegisterOtp")
    public PortalResponse verifyRegisterOtp(@RequestBody VerifyMailOtp req){
        return registerService.verifyOtp(req);
    }

    @PostMapping("/login")
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

    @PostMapping("/uploadImage")
    public PortalResponse imageUpload(@RequestParam String token,
                                      @RequestParam Long userID,
                                      @RequestParam Long category,
                                      @RequestParam ArrayList<MultipartFile> fileFullWidth,
                                      @RequestParam ArrayList<MultipartFile> fileThumbnail,
                                      @RequestParam ArrayList<MultipartFile> filePortrait,
                                      @RequestParam ArrayList<MultipartFile> fileSquare,
                                      @RequestParam ArrayList<MultipartFile> fileHero)throws IOException {
        return imageService.imageUpload(token,userID,category, fileFullWidth, fileThumbnail, filePortrait, fileSquare, fileHero);
    }

}
