package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    LogoutService logoutService;

    @Autowired
    UserItemsService userItemsService;

    @Autowired
    UserRequestService userRequestService;

    @Autowired
    ItemDataService itemDataService;

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

    @PostMapping("/verifySmsOtp")
    public PortalResponse verifySmsOtp(@RequestBody VerifySmsOtp req){
        return loginSmsService.verifyOtp(req);
    }

    @PostMapping("/logoutUser")
    public PortalResponse logoutUser(@RequestBody LogoutRequest req){
        return logoutService.logoutUser(req);
    }

    @PostMapping("/fetchUserItems")
    public PortalResponse myItems(@RequestBody TokenRequest req){
        return userItemsService.myItems(req);
    }

    @PostMapping("/removeUserItem")
    public PortalResponse removeItems(@RequestBody AcceptItemRequest req){
        return  userItemsService.removeItems(req);
    }

    @PostMapping("/fetchUserInbox")
    public PortalResponse fetchInboxItems(@RequestBody TokenRequest req){
        return userRequestService.myInbox(req);
    }

    @PostMapping("/acceptUserInboxItem")
    public PortalResponse acceptInboxItems(@RequestBody AcceptItemRequest req){
        return userRequestService.acceptRequest(req);
    }

    @PostMapping("/fetchItemData")
    public PortalResponse fetchItemData(@RequestBody ItemDataRequest request){
        return itemDataService.itemData(request);
    }
}
