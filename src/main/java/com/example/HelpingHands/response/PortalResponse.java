package com.example.HelpingHands.response;

import com.example.HelpingHands.entity.DonateEntity;
import lombok.Data;

import java.util.List;


@Data
public class PortalResponse {
    private String statusCode;
    private String message;
    private String requestId;
    private Object data;
    private List<DonateEntity> data2;

    public static PortalResponse commonSuccessResponse( String message, String requestId, Object data){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData(data);

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


    public static PortalResponse customSuccessResponse(String message, String requestId, List<DonateEntity> data2){
        PortalResponse portalResponse=new PortalResponse();
        portalResponse.setStatusCode("200");
        portalResponse.setMessage(message);
        portalResponse.setRequestId(requestId);
        portalResponse.setData2(data2);

        return portalResponse;
    }


}

