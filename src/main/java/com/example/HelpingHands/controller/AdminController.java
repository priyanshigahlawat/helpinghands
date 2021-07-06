package com.example.HelpingHands.controller;

import com.example.HelpingHands.request.*;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.service.AdminService;
import com.example.HelpingHands.service.AdminStatsService;
import com.example.HelpingHands.service.CreateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    CreateAdminService createAdminService;


    @Autowired
    AdminStatsService adminStatsService;

    @PostMapping("/fetchItem")
    public PortalResponse fetchData(@RequestBody TokenRequest request){
        return adminService.fetchData(request);
    }

    @PostMapping("/approveItem")
    public PortalResponse approveItem(@RequestBody ApproveItemRequest request){
        return adminService.approveItem(request);
    }

    @PostMapping("/rejectItem")
    public PortalResponse rejectItem(@RequestBody RejectItemRequest request){
        return adminService.rejectItem(request);
    }

    @PostMapping("/createAdmin")
    public PortalResponse createAdmin(@RequestBody CreateAdminRequest request){
        return  createAdminService.createAdmin(request);
    }

    @PostMapping("/totalRegisters")
    public PortalResponse totalRegisters(@RequestBody TokenRequest request){
        return adminStatsService.totalRegisters(request);
    }

    @PostMapping("/totalDonors")
    public PortalResponse totalDonors(@RequestBody TokenRequest request){
        return adminStatsService.totalDonors(request);
    }

    @PostMapping("/totalPendingRequests")
    public PortalResponse totalPendingRequests(@RequestBody TokenRequest request){
        return adminStatsService.totalPendingRequests(request);
    }

    @PostMapping("/totalDonorsOnADay")
    public PortalResponse totalDonorsOnADay(@RequestBody DonorsRequest request){
        return adminStatsService.totalDonorsOnADay(request);
    }

    @PostMapping("/totalRegistersOnADay")
    public PortalResponse totalRegistersOnADay(@RequestBody DonorsRequest request){
        return adminStatsService.totalRegistersOnADay(request);
    }
}