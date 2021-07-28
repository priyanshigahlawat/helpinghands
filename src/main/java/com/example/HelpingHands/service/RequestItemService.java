package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.RequestEntity;
import com.example.HelpingHands.repository.DonateRepository;
import com.example.HelpingHands.repository.RequestRepository;
import com.example.HelpingHands.request.ItemRequest;
import com.example.HelpingHands.request.RequestedItemRequest;
import com.example.HelpingHands.response.PortalResponse;
import com.example.HelpingHands.utility.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@Service
public class RequestItemService {

    @Autowired
    VerifyToken verifyToken;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    DonateRepository donateRepository;

    public PortalResponse requestItems(@RequestBody ItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){
            Optional<DonateEntity> donateEntity = donateRepository.findById(request.getItemID());
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setDateOfReq(new Date());
            requestEntity.setReqID(request.getUserID());
            requestEntity.setDonorID(donateEntity.get().getUserID());
            requestEntity.setItemID(request.getItemID());
            requestRepository.save(requestEntity);
            return PortalResponse.commonSuccessResponse("item Requested","",requestEntity);
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }

    //==================================================================================================================

    public PortalResponse ItemRequested(@RequestBody RequestedItemRequest request){
        boolean flag = verifyToken.verifyToken(request.getUserID(),request.getToken());
        if(flag == true){

            Long count = requestRepository.getItemsRequested(request.getUserID(), request.getItemID());

            return PortalResponse.commonSuccessResponse(String.valueOf(count),"","");
        }
        else {
            return PortalResponse.commonErrorResponse("invalid user", "", "");
        }
    }
}