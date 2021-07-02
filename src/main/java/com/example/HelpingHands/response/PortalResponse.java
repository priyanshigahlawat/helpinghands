package com.example.HelpingHands.response;

import lombok.Data;


@Data
public class PortalResponse {
    private String statusCode;
    private String message;
    private String token;
    private String requestId;
    private Object data;

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


}

