package com.example.HelpingHands.response;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.InboxRecord;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.request.MyInboxItems;
import lombok.Data;
import lombok.ToString;

import java.util.*;

import java.util.List;


@Data
@ToString
public class PortalResponse {
    private String statusCode;
    private String message;
    private String requestId;
    private Object data;
    private List<DonateEntity> data2;
    private List<InboxRecord> data3;

    public static PortalResponse commonSuccessResponse( String message, String requestId, Object data){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData(data);

        return portalResponse;
    }

    public static PortalResponse customObjectSuccessResponse( String message, String requestId, List<InboxRecord> data3){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData(data3);

        return portalResponse;
    }

    public static PortalResponse customCountSuccessResponse(String  message, String requestId){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);

        return portalResponse;
    }

    public static PortalResponse customSuccessResponse(String message, String requestId, List<DonateEntity> data2){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData2(data2);

        return portalResponse;
    }

    public static PortalResponse commonErrorResponse( String message, String requestId, Object data){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("202");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData(data);

        return portalResponse;
    }




}

