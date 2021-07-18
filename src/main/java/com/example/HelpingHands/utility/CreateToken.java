package com.example.HelpingHands.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateToken {

    @Value("${signingKey}")
    private String key;

    public String generateToken(String id){
        String token= Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*7200))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
        return token;
    }
}
