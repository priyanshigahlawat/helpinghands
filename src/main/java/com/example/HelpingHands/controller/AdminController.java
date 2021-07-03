package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.ApproveItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/fetchItem")
    public PortalResponse fetchData(@RequestBody TokenRequest request){
        return adminService.fetchData(request);
    }

    @PostMapping("/approveItem")
    public PortalResponse approveItem(@RequestBody ApproveItemRequest request){
        return adminService.approveItem(request);
    }
}
