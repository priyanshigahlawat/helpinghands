package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.RequestEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.RequestRepository;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.AcceptItemRequest;
import com.example.HelpingHands.request.RemoveItemRequest;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.MailUtility;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserRequestService {
    @Autowired
    VerifyToken verifyToken;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    DonateRepository donateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailUtility mailUtility;

    public PortalResponse myInbox(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
            if(flag == true){
                List<Object[]> list =  requestRepository.fetchInboxRecord(request.getUserID());
                return PortalResponse.customObjectSuccessResponse("fetchedData","",list);
            }
            else {
                return PortalResponse.commonErrorResponse("invalid user", "", "");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return PortalResponse.commonErrorResponse("No matched record found", "", "");
        }
    }

    //==========================================Reject==================================================================

    public PortalResponse rejectRequest(@RequestBody RemoveItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Optional<RequestEntity> donateEntity = requestRepository.findById(request.getId());
            requestRepository.delete(donateEntity.get());
            return PortalResponse.commonSuccessResponse("Removed item","", "");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //====================================Accept========================================================================

    public PortalResponse acceptRequest(@RequestBody AcceptItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = donateRepository.findById(request.getItemID());
            donateEntity.get().setAprrovedStatus(1L);
            donateEntity.get().setAdminMessage("approved");
            donateRepository.save(donateEntity.get());
            Long id = donateEntity.get().getUserID();
            Optional<UserEntity> userEntity = userRepository.findById(id);
            String email = userEntity.get().getEmail();
            String mailDesc = "Your request for " + donateEntity.get().getItemName() + " has been approved by the donor";
            mailUtility.sendMail(email, mailDesc);

            return PortalResponse.commonSuccessResponse("Item approved","", "");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }
}