package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.InboxRecord;
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

import java.util.*;
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

    //=============================================INBOX RECORD=========================================================

    public PortalResponse myInbox(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
            if(flag == true){

                List<DonateEntity> donateEntityList = donateRepository.listOfDonations();
                List<UserEntity> userEntityList = userRepository.listOfUsers();
                List<RequestEntity> requestEntityList = requestRepository.listOfRequests(request.getUserID());
                List<InboxRecord> inboxRecordList = new ArrayList<InboxRecord>();

                for (int i = 0; i < requestEntityList.size(); ++i){
                    RequestEntity requestEntity = requestEntityList.get(i);
                    DonateEntity donateEntity = donateRepository.findByItemID(requestEntity.getItemID());
                    Optional<UserEntity> userEntity = userRepository.findById(requestEntity.getReqID());
                    inboxRecordList.add(new InboxRecord());
                    inboxRecordList.get(i).setName(userEntity.get().getName());
                    inboxRecordList.get(i).setEmail(userEntity.get().getEmail());
                    inboxRecordList.get(i).setPhone(userEntity.get().getPhone());
                    inboxRecordList.get(i).setRequest_id(requestEntity.getReqID());
                    inboxRecordList.get(i).setDonor_id(requestEntity.getDonorID());
                    inboxRecordList.get(i).setItem_id(donateEntity.getItemID());
                    inboxRecordList.get(i).setApprovedStatus(donateEntity.getAprrovedStatus());
                    inboxRecordList.get(i).setExpireStatus(donateEntity.getExpireStatus());
                    inboxRecordList.get(i).setItem_name(donateEntity.getItemName());
                    inboxRecordList.get(i).setItem_description(donateEntity.getItemDesc());
                    inboxRecordList.get(i).setDate(donateEntity.getDate());
                }

                return PortalResponse.customObjectSuccessResponse("fetchedData","",inboxRecordList);

            }
            else {
                return PortalResponse.commonErrorResponse("invalid user", "", "");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return PortalResponse.commonErrorResponse("No matched record found", "", "");
        }
    }

    //=======================================OUTBOX RECORD==============================================================

    public PortalResponse myOutbox(@RequestBody TokenRequest request){
        try{
            boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
            if(flag == true){

                List<DonateEntity> donateEntityList = donateRepository.listOfDonations();
                List<UserEntity> userEntityList = userRepository.listOfUsers();
                List<RequestEntity> requestEntityList = requestRepository.listOfRequests2(request.getUserID());
                List<InboxRecord> inboxRecordList = new ArrayList<InboxRecord>();

                for (int i = 0; i < requestEntityList.size(); ++i){
                    RequestEntity requestEntity = requestEntityList.get(i);
                    DonateEntity donateEntity = donateRepository.findByItemID(requestEntity.getItemID());
                    Optional<UserEntity> userEntity = userRepository.findById(requestEntity.getDonorID());
                    inboxRecordList.add(new InboxRecord());
                    inboxRecordList.get(i).setName(userEntity.get().getName());
                    inboxRecordList.get(i).setEmail(userEntity.get().getEmail());
                    inboxRecordList.get(i).setPhone(userEntity.get().getPhone());
                    inboxRecordList.get(i).setRequest_id(requestEntity.getReqID());
                    inboxRecordList.get(i).setDonor_id(requestEntity.getDonorID());
                    inboxRecordList.get(i).setItem_id(donateEntity.getItemID());
                    inboxRecordList.get(i).setApprovedStatus(donateEntity.getAprrovedStatus());
                    inboxRecordList.get(i).setExpireStatus(donateEntity.getExpireStatus());
                    inboxRecordList.get(i).setItem_name(donateEntity.getItemName());
                    inboxRecordList.get(i).setItem_description(donateEntity.getItemDesc());
                    inboxRecordList.get(i).setDate(donateEntity.getDate());
                }

                return PortalResponse.customObjectSuccessResponse("fetchedData","",inboxRecordList);

            }
            else {
                return PortalResponse.commonErrorResponse("invalid user", "", "");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return PortalResponse.commonErrorResponse("No matched record found", "", "");
        }
    }

    //==========================================Reject==================================================================

    public PortalResponse rejectRequest(@RequestBody RemoveItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            RequestEntity donateEntity = requestRepository.rejectReq(request.getItemID(),request.getId());
            requestRepository.delete(donateEntity);
            return PortalResponse.commonSuccessResponse("Removed item","", donateEntity);
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
            donateEntity.get().setExpireStatus(1L);
            donateEntity.get().setAdminMessage("approved");
            donateRepository.save(donateEntity.get());
            Long id = donateEntity.get().getUserID();

            List<RequestEntity> requestRepositoryList = requestRepository.listOfitemReq(request.getItemID());
            for (int i = 0; i < requestRepositoryList.size(); ++i){
                requestRepositoryList.get(i).setRequestStatus(1L);
                requestRepository.save(requestRepositoryList.get(i));
            }

            Optional<UserEntity> userEntity1 = userRepository.findById(request.getDonorID());
            Optional<UserEntity> userEntity = userRepository.findById(request.getUserID());
            String email1 = userEntity1.get().getEmail();
            String mailDesc = "Your request for " + donateEntity.get().getItemName() + " has been approved by the donor. For further details contact " + userEntity.get().getEmail() + " email and " + userEntity.get().getPhone() + " contact." ;
            mailUtility.sendMail(email1, mailDesc);
//
//            String email = userEntity.get().getEmail();
//            String mailDesc1 = "Your item " +  donateEntity.get().getItemName() + " has been donated";
//            mailUtility.sendMail(email, mailDesc1);

            return PortalResponse.commonSuccessResponse("Item approved","", "");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }
}