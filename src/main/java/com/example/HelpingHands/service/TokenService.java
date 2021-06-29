package com.example.HelpingHands.service;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.TokenRequest;
import com.example.HelpingHands.response.PortalResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    UserRepository userRepository;

    public PortalResponse verifyToken(@RequestBody TokenRequest req){
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity = userRepository.findByEmail(req.getEmail());

        try{
            Claims claim= Jwts.parser()
                    .setSigningKey(userEntity.getSigningKey())
                    .parseClaimsJws(userEntity.getToken())
                    .getBody();

            System.out.println(claim.getId() + "   " + claim.getExpiration());

            if(claim.getExpiration().before(new Date(System.currentTimeMillis()))){
                portalResponse.setMessage("invalid user");
                portalResponse.setStatusCode("202");
            }
            else if(userEntity.getToken().equals(req.getToken())){
                portalResponse.setMessage("valid user");
                portalResponse.setStatusCode("200");
            } else {
                portalResponse.setMessage("invalid user");
                portalResponse.setStatusCode("202");
            }
        } catch (SignatureException ex){
            portalResponse.setMessage("invalid user");
            portalResponse.setStatusCode("202");
        } catch (ExpiredJwtException ex){
            portalResponse.setMessage("invalid user");
            portalResponse.setStatusCode("202");
        }
        return portalResponse;
    }
}
