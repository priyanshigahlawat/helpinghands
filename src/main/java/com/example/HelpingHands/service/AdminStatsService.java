package com.example.HelpingHands.service;

import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.DonorsRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AdminStatsService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonateRepository donateRepository;

    //============================TOTAL NO OF USERS TILL NOW============================================================

    public PortalResponse totalRegisters (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxUsers = userRepository.max();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxUsers),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF DONATE TILL NOW===========================================================

    public PortalResponse totalDonors (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxDonors = donateRepository.max();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxDonors),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF PENDING REQUESTS==========================================================

    public PortalResponse totalPendingRequests (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxPendingRequests = donateRepository.getPendingRequests();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxPendingRequests),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF LOCKED USERS==============================================================

    public PortalResponse totalLocked (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxPendingRequests = userRepository.fetchLockedUsers();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxPendingRequests),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF APPROVED REQUESTS=========================================================

    public PortalResponse totalApprovedRequests (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxPendingRequests = donateRepository.getTotalApproved();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxPendingRequests),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF REJECTED REQUESTS=========================================================

    public PortalResponse totalRejectedRequests (@RequestBody TokenRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Long maxPendingRequests = donateRepository.getTotalRejected();
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxPendingRequests),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF DONATES ON A PARTICULAR DATE==============================================

    public PortalResponse totalDonorsOnADay (@RequestBody DonorsRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            LocalDate currentDate = LocalDate.now();
            Date today = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strToday = dateFormat.format(today);
            Long maxDonorsOnADay = donateRepository.fetchDonorsADay(strToday);

            return PortalResponse.customCountSuccessResponse(String.valueOf(maxDonorsOnADay),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //============================TOTAL NO OF REGISTERS ON A PARTICULAR DATE============================================

    public PortalResponse totalRegistersOnADay (@RequestBody DonorsRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            LocalDate currentDate = LocalDate.now();
            Date today = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strToday = dateFormat.format(today);
            Long maxRegistersOnADay = userRepository.fetchRegistersADay(strToday);
            return PortalResponse.customCountSuccessResponse(String.valueOf(maxRegistersOnADay),"");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }
}