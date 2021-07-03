package com.example.HelpingHands.utility;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VerifyToken {

    @Autowired
    UserRepository userRepository;

    @Value("${signingKey}")
    private String key;

    public boolean verifyToken(Long userID, String token){

        boolean flag;
        Optional<UserEntity> userEntity = userRepository.findById(userID);

        try {
            Claims claim = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(userEntity.get().getToken())
                    .getBody();

            System.out.println(claim.getId() + "   " + claim.getExpiration());

            if (claim.getExpiration().before(new Date(System.currentTimeMillis()))) {

                flag = false;

            } else if (userEntity.get().getToken().equals(token)) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (SignatureException ex) {
            flag = false;
        } catch (ExpiredJwtException ex) {
            flag = false;
        }
        return flag;
    }
}
