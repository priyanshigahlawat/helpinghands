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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    UserRepository userRepository;


    @Value("${signingKey}")
    private String key;

    public PortalResponse verifyToken(@RequestBody TokenRequest req) {
        PortalResponse portalResponse = new PortalResponse();
        UserEntity userEntity = userRepository.findByEmail(req.getEmail());

        try {
            Claims claim = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(userEntity.getToken())
                    .getBody();

            System.out.println(claim.getId() + "   " + claim.getExpiration());

            if (claim.getExpiration().before(new Date(System.currentTimeMillis()))) {


                return portalResponse.commonErrorResponse("invalid user", "", "");
            } else if (userEntity.getToken().equals(req.getToken())) {

                return portalResponse.commonSuccessResponse("valid user", "", "");
            } else {
                return portalResponse.commonErrorResponse("invalid user", "", "");
            }
        } catch (SignatureException ex) {
            return portalResponse.commonErrorResponse("invalid user", "", "");

        } catch (ExpiredJwtException ex) {

            return portalResponse.commonErrorResponse("invalid user", "", "");
        }

    }
}
