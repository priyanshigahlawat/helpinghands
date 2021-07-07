package com.example.HelpingHands.DAOImplementation;

import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.repository.UserRepository;
import com.example.HelpingHands.request.RegisterRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserInfo {

    @Autowired
    UserRepository userRepository;

    public void saveInfo(RegisterRequest req, UserEntity userEntity){

        userEntity.setEmail(req.getEmail());
        userEntity.setName(req.getName());
        userEntity.setPhone(req.getPhone());
        userEntity.setPassword(req.getPassword());
        userEntity.setAdminStatus(0L);
        userEntity.setActiveStatus(1L);
        userRepository.save(userEntity);
    }
}
